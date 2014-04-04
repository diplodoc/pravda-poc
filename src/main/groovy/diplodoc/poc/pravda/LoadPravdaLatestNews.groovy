package diplodoc.poc.pravda

import org.jsoup.Jsoup

/**
 * @author yaroslav.yermilov
 */
def url = 'http://www.pravda.com.ua/news/'

println "Loading '${url}'..."

def newsListDocument = Jsoup.connect(url).get()

def newsEntities = newsListDocument.select('dd').collect {
    def caption = it.select('dd > a').text()

    def link = it.select('dd > a').attr('href')
    link = (link.startsWith('http') ? link : "http://www.pravda.com.ua${link}")

    def articleDocument = Jsoup.connect(link).get()
    def text = articleDocument.select('div.text').text()

    new NewsEntity(
        caption: caption,
        link: link,
        text: text
    )
}

println 'News elements:'
println '================'
newsEntities.each {
    println """
Id:          ${it.id}
Caption:     ${it.caption}
Link:        ${it.link}
Time:        ${it.time}
Text:        ${it.text}
"""
}
println '================'
