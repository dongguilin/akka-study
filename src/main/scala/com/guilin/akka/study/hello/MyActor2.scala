package com.guilin.akka.study.hello

import akka.actor.{Actor, ActorLogging}

/**
  * Created by guilin on 2017/7/21.
  */
class MyActor2 extends Actor with ActorLogging {

  import MyActor2._

  override def receive: Receive = {
    case Greeting(greeter) => log.info(s"I was greeted by $greeter.")
    case Goodbye => log.info("Someone said goodbye to me.")
  }
}

object MyActor2 {

  case class Greeting(from: String)

  case object Goodbye

}
