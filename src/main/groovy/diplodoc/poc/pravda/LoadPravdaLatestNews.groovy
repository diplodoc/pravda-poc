package diplodoc.poc.pravda

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * @author yaroslav.yermilov
 */
def url = 'http://www.pravda.com.ua/news/'

println "Loading '${url}'..."

def document = Jsoup.connect(url).get();
println 'Loaded document:'
println '================'
println document
println '================'

println 'News elements:'
println '================'
document.select('dd').each {
    println """
===
Caption:     ${it.select('dd > a').text()}
Description: ${it.select('dd > p > a').text()}
Link:        ${it.select('dd > a').attr 'href'}
===
${it}
===
"""
}
println '================'
