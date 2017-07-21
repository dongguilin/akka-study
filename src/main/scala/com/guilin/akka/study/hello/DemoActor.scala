package com.guilin.akka.study.hello

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by guilin on 2017/7/21.
  */
class DemoActor(magicNumber: Int) extends Actor {
  override def receive: Receive = {
    case x: Int => sender() ! (x + magicNumber)
  }
}

class SomeOtherActor extends Actor with ActorLogging {

  // Props(new DemoActor(42)) would not be safe
  context.actorOf(DemoActor.props(42), "demo")

  override def receive: Receive = {
    case _ => log.info("...")
  }
}

object DemoActor {
  def props(magicNumber: Int): Props = Props(new DemoActor(magicNumber))
}
