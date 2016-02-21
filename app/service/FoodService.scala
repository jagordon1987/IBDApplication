package service

import javax.inject.Inject
import persistence.{Food, FoodDAO}

import scala.concurrent.Future


class FoodService @Inject()(foodDAO: FoodDAO) {


  def addNewFood(food: Food): Future[String] = {
    foodDAO.addNewFood(food)
  }

  def deleteFood(id: Long): Future[Int] = {
    foodDAO.deleteFood(id)
  }

  def getFood(id: Long): Future[Option[Food]] = {
    foodDAO.getFood(id)
  }

  def getAllFoods: Future[Seq[Food]] = {
    foodDAO.getAllFoods
  }

//  def updateFood(id: Long, food: Food): Future[Option[Food]] = {
//    foodDAO.updateFood(id, food)
//  }

}
