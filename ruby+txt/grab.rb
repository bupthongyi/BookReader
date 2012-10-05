# UTF-8 Encoding

require 'rubygems'
require 'mechanize'

agent = Mechanize.new { |agent|
  #agent.user_agent_alias = 'Mac Safari'     # surrogate browser
}

############
## STEP 1 ##
############

# start page
page = agent.get('http://www.daomubiji.com/dao-mu-bi-ji-quan-ji')
# all links on that page
links = Hash.new
# start looping
counter = 1
page.links_with(:attributes => /盗墓笔记8\s+/).each do |link|
    # puts link.href
    links[counter.to_s] = link.href
    counter = counter + 1
end

############
## STEP 2 ##
############

links.map do |key, value|
  # page object
  page = agent.get(value)
  # header of the chapter
  header = page.search('div.bg').search('h1').text
  # raw text material need screening
  text = page.search('div.content').search('p')
  result = ''   # result 
  # screening
  text.map do |body|
    if body.children[1] == nil
      result = result + body.children[0]
    else
      break
    end
  end
  # output to file
  file = File.open("ch" + key + ".txt", "w")
  file.puts(header + "\n")
  file.puts(result)
  file.close
  # indicator
  puts "Chapter " + header + ": done"
  # don't crash the website
  sleep(0.1)
end
