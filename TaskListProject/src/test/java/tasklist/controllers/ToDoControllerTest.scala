package tasklist.controllers

import org.junit.runner.RunWith
import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestContextManager
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import tasklist.service.ToDoService


@RunWith(classOf[SpringRunner])
@WebMvcTest(Array(classOf[ToDoController]))
class ToDoControllerTest extends AnyFunSpec with GivenWhenThen {

  @Autowired
  var mvc: MockMvc = _
  @MockBean
  val toDoServiceMock: ToDoService = null

  new TestContextManager(this.getClass).prepareTestInstance(this)

  test("A HelloWorld Test") {
    Given("a customer id")
    val id = 1l
    val expectedCustomer = new Customer(id, "Bob")
    when(customerService.findCustomer(id)).thenReturn(expectedCustomer)
  }

     in {
    mvc.perform(get("/tasklist")) should be ("Hello World!")
  }

  "A Get TaskList Test" should "return TaskList Items" in {
    //doReturn(Future.successful(List[ToDoClass]())).when(toDoServiceMock).getTaskList()
    //toDoController.getTaskList() should be (Mockito.anyString)
  }
}
