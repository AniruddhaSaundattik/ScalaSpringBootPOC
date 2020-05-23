package tasklist.model


case class ToDoClass(id: Int, name: String, desc: String){
  override def toString: String = {
    s"ID is $id " + "    \n" +
    s"Task to be done is $name" + "\n" +
    s"Details of the task are $desc"

  }
}
