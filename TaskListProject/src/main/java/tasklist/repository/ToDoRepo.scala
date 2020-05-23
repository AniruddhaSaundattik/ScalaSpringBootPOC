package tasklist.repository

import java.sql.{Connection, DriverManager, ResultSet, Statement}

import org.springframework.stereotype.Repository
import tasklist.model.RequestDTO

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Repository
class ToDoRepo {
  def updateTaskListItemInDb(id: Int, requestDTO: RequestDTO): Unit = {
    val st: Statement = getConnection
    st.executeUpdate(s"UPDATE TBL_TASKLIST SET task_name= '${requestDTO.name}', task_description='${requestDTO.desc}' WHERE id = $id ")
  }


  def getConnection = {
    var st: Statement = null
    try {
      Class.forName("org.h2.Driver")
      val conn: Connection = DriverManager.getConnection("jdbc:h2:C:/data/sample", "sa", "")
      st = conn.createStatement()
    }
    catch {
      case e: Exception => println("Exception occured" + e)
    }
    st
  }

  def getTaskListFromDb(): Future[Option[ResultSet]] = Future {
    val st: Statement = getConnection
    Option(st.executeQuery("select * from tbl_tasklist"))
  }

  def removeTaskListItemFromDb(id: Int): Future[Int] = Future {
    val st: Statement = getConnection
    st.executeUpdate(s"DELETE FROM TBL_TASKLIST where ID = $id")
  }

  def addTaskListItemToDb(requestDTO: RequestDTO): Int = {
      val st: Statement = getConnection
      st.executeUpdate(s"Insert into TBL_TASKLIST (task_name,task_description) VALUES('${requestDTO.name}', '${requestDTO.desc}')")
  }
}
