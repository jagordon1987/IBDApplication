package controllers.application

import javax.inject.Inject

import play.api.mvc._
import service.FoodService
import scala.concurrent.ExecutionContext.Implicits.global


class FoodApplicationController @Inject()(foodService: FoodService) extends Controller {

  def getAllFoods = Action.async { implicit request =>
    foodService.getAllFoods map { foods =>
      Ok(views.html.foods(foods))
    }
  }

  def deleteFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Delete food called")
  }

  def getFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Get food called")
  }

  def addNewFood() = Action { implicit request =>
    Ok("FoodApplicationController - Add new food called")
  }

  def updateFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Update food called")
  }

}
