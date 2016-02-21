package controllers.application

import javax.inject.Inject

import play.api.mvc._
import service.FoodService
import scala.concurrent.ExecutionContext.Implicits.global


case class CreateFood(name: String, additionalInformation: String)
class FoodApplicationController @Inject()(foodService: FoodService) extends Controller {

  def getAllFoods = Action.async { implicit request =>
    foodService.getAllFoods map { foods =>
      Ok(views.html.foods(foods))
    }
  }

  def deleteFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Delete food called")
  }

  def getFood(id: Long) = Action.async  { implicit request =>
    foodService.getFood(id) map {
        case Some(x) => Ok(views.html.food(x))
        case None => BadRequest("Bad request")
    }
  }

  def addNewFood() = Action { implicit request =>
    Ok("FoodApplicationController - Add new food called")
  }

  def updateFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Update food called")
  }

}
