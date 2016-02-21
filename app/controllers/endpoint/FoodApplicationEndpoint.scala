package controllers.endpoint

import javax.inject.Inject

import play.api.mvc.{Controller, Action}
import service.FoodService
import scala.concurrent.ExecutionContext.Implicits.global

class FoodApplicationEndpoint @Inject()(foodService: FoodService) extends Controller  {


  def getAllFoods = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Get all foods called")
  }

  def deleteFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Delete food called")
  }

  def getFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Get food called")
  }

  def addNewFood() = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Add new food called")
  }

  def updateFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Update food called")
  }
}
