package tasklist.controllers

import com.google.gson.Gson
import com.typesafe.scalalogging.{LazyLogging, Logger}
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.web.bind.annotation._
import tasklist.model.{RequestDTO, ToDoClass}
import tasklist.service.{ToDoService, ToDoServiceImpl}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

@RestController
class ToDoController extends LazyLogging{
  //@Qualifier("ToDoServiceImpl")
  @Autowired
  var toDoService: ToDoService = _

  @GetMapping(Array("hello"))
  def helloWorld(): String = {
    logger.debug("Hello World")
    "Hello World!"
  }


  @RequestMapping(Array("tasklist"))
  def getTaskList() = {
    var output: String = ""
    val taskList = Await.result(toDoService.getTaskList(), 1000 second)
    taskList.foreach(x => output += x)
    output
  }

  @PostMapping(value = Array("additem"), consumes = Array("application/json"))
  def addTaskListItem(@RequestBody request: String) = {
    val requestDTO = new Gson().fromJson(request, classOf[RequestDTO])
    toDoService.addTaskListItem(requestDTO)
  }

  @PutMapping(value = Array("edititem/{id}"), consumes = Array("application/json"))
  def updateTaskListItem(@PathVariable id: Int, @RequestBody request: String) = {
    val requestDTO = new Gson().fromJson(request, classOf[RequestDTO])
    toDoService.editTaskListItem(id,requestDTO)
  }

  @DeleteMapping(value = Array("removeitem/{id}"))
  def deleteTaskListItem(@PathVariable id: Int): Int = {
    Await.result(toDoService.deleteTaskListItem(id), 1000 second)
  }

}
