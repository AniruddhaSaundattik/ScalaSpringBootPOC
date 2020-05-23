
package tasklist.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tasklist.model.{RequestDTO, ToDoClass}
import tasklist.repository.ToDoRepo

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

@Service
class ToDoServiceImpl extends ToDoService {
  @Autowired
  var toDoRepo: ToDoRepo = _

  override def getTaskList() =  {
    var lst: List[ToDoClass] = Nil
    val result = toDoRepo.getTaskListFromDb()
    result.map{
      r => if(r.isDefined){
        while (r.get.next()) {
          val id: Int = r.get.getInt("ID")
          val name: String = r.get.getString("TASK_NAME")
          val desc: String = r.get.getString("TASK_DESCRIPTION")
          val toDoClass = new ToDoClass(id, name, desc)
          lst = lst.::(toDoClass)
        }
      }
    lst
    }
  }

  override def deleteTaskListItem(id: Int):Future[Int] = {
    toDoRepo.removeTaskListItemFromDb(id)
  }

  override def addTaskListItem(requestDTO: RequestDTO): Int = {
    toDoRepo.addTaskListItemToDb(requestDTO)
  }

  override def editTaskListItem(id: Int, requestDTO: RequestDTO): Unit = {
    toDoRepo.updateTaskListItemInDb(id, requestDTO)
  }
}

