package diplodoc.poc.pravda

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * @author yaroslav.yermilov
 */
def url = 'http://www.pravda.com.ua/news/'

println "Loading '${url}'..."

Document document = Jsoup.connect(url).get();
println 'Loaded document:'
println '================'
println document
println '================'
