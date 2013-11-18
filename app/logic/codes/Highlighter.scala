package logic.codes

import play.api.templates.Html
import scala.xml._

object Highlighter {

  
  private def renderToken(t: Token) = t match {
    	case TokenKeyword(s) => <strong class="style_keyword">{s}</strong>
	    case TokenComment(s) => <span class="style_comment">{s}</span>
	    case TokenConstant(s) => <span class="style_constant">{s}</span>
	    case TokenFunc(s) => <span class="style_function">{s}</span>
	    case TokenType(s) => <span class="style_type">{s}</span>
	    case TokenInt(s) => <span class="style_int">{s}</span>
	    case TokenFloat(s) => <span class="style_float">{s}</span>
	    case TokenString(s) => <span class="style_string">{s}</span>
	    case TokenTab() => scala.xml.Text("\t")
	    case TokenNl() => {scala.xml.Text("\n")} ++ <span class="lineNr"></span>
	    case TokenOther(s) => scala.xml.Text(s)
	    case TokenIdent(s) => scala.xml.Text(s)
    }
  
  def highlightCode(code: String): Html = {
    val tokens = WurstTokenizer.tokenize(code)
    Html(tokens.view.map(renderToken).mkString)
  }
  
}