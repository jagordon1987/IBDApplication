package persistence

import javax.inject.Singleton

import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global


case class Food(id: Long, name: String, additionalInformation: String)

class FoodTableDefinition(tag: Tag) extends Table[Food](tag, "foods") {

  def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
  def name = column[String]("name")
  def additionalInformation = column[String]("additional_information")


  override def * =
    (id, name, additionalInformation) <>(Food.tupled, Food.unapply)
}

@Singleton
class FoodDAO {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val foods = TableQuery[FoodTableDefinition]

  def addNewFood(food: Food): Future[String] = {
    dbConfig.db.run(foods += food).map(res => "Your new foods has been added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def deleteFood(id: Long): Future[Int] = {
    dbConfig.db.run(foods.filter(_.id === id).delete)
  }

  def getFood(id: Long): Future[Option[Food]] = {
    dbConfig.db.run(foods.filter(_.id === id).result.headOption)
  }

  def getAllFoods: Future[Seq[Food]] = {
    dbConfig.db.run(foods.result)
  }

//  def updateFood(id: Long, food: Food): Future[Option[Food]] = {
//
//  }


}
