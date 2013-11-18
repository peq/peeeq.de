package controllers

import play.api.mvc.{Action, Controller}
import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession
import scala.slick.direct._
import scala.slick.direct.AnnotationMapper._
import scala.reflect.runtime.universe
import scala.slick.jdbc.StaticQuery.interpolation
import model.CodePaste
import scala.slick.driver.MySQLDriver
import play.api.db._
import dbhelper.peqDB
import model.CodePastes
import play.api.templates.Html
import play.api.mvc.Request
import play.api.data._
import play.api.data.Forms._
import model.CodePaste
import model.CodePaste
import scala.slick.jdbc.StaticQuery
import model.CodePaste
import model.CodePaste
import org.joda.time.DateTime
import logic.codes.Highlighter

object CodePasteController extends Controller {

  // code paste form 
  case class CodePasteFormData(code: String, lang: String)
  val codePasteForm: Form[CodePasteFormData] = Form(
    mapping(
      "code" -> text,
      "lang" -> text
    )(CodePasteFormData.apply)(CodePasteFormData.unapply)
  )

  /**
   * shows the form for posting
   */
  def index = Action { implicit request =>
    Ok(views.html.postCode.render(codePasteForm, request))
  }

  /**
   * post handler for posts
   */
  def post = Action { request =>
    val pasted = codePasteForm.bindFromRequest()(request).get


    peqDB.withTransaction { session =>
      // get a new id 
      val maxId = StaticQuery.queryNA[Int]("SELECT MAX(`id`) FROM mapping_nopaste_jass").list()(session).head
      val newId = 1 + maxId

      val newCode = CodePaste(
        id = newId,
        code = pasted.code,
        datum = new DateTime(),
        userid = 0,
        codetype = "wurst",
        key = "")

      CodePastes.insert(newCode)(session)
      Redirect(routes.CodePasteController.showCode(newId))
    }
  }


  /**
   * show code with a specific id
   */
  def showCode(id: Int) = Action {
    val result = peqDB.querySingle(Query(CodePastes).filter(_.id === id))

    result match  {
      case None => NotFound(views.html.index.render("No code found"))
      case Some(code) => {
        
        var c: String = code.code 
        if (code.id < 6930) {
          // legacy entries
          // conversion was done at save time, undo it
          c = c.replaceAll("&gt;", ">").replaceAll("&lt", "<")
        }
        
        val codeHtml : Html = Highlighter.highlightCode(c)
        Ok(views.html.codes.render(codeHtml))
      }
    }

  }


}