package diplodoc.poc.pravda

import org.jsoup.Jsoup

/**
 * @author yaroslav.yermilov
 */
def url = 'http://www.pravda.com.ua/news/'

println "Loading '${url}'..."

def document = Jsoup.connect(url).get();

def newsEntities = document.select('dd').collect {
    def caption = it.select('dd > a').text()

    def description = it.select('dd > p > a').text()

    def link = it.select('dd > a').attr('href')
    link = (link.startsWith('http') ? link : "http://www.pravda.com.ua${link}")

    new NewsEntity(
        caption: caption,
        description: description,
        link: link
    )
}

println 'News elements:'
println '================'
newsEntities.each {
    println """
Caption:     ${it.caption}
Description: ${it.description}
Link:        ${it.link}
"""
}
println '================'
