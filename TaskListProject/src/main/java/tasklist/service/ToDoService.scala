package tasklist.service

import tasklist.model.{RequestDTO, ToDoClass}

import scala.concurrent.Future

trait ToDoService {
  def editTaskListItem(id: Int, requestDTO: RequestDTO)

  def addTaskListItem(requestDTO: RequestDTO): Int

  def getTaskList(): Future[List[ToDoClass]]
  def deleteTaskListItem(id: Int): Future[Int]

}
