# UTF-8 Encoding

require 'rubygems'
require 'mechanize'

agent = Mechanize.new { |agent|
  #agent.user_agent_alias = 'Mac Safari'     # surrogate browser
}

enter = agent.get('http://www.daomubiji.com/dao-mu-bi-ji-quan-ji')  # start page

links = Hash.new    # all links on that page
counter = 1
enter.links_with(:attributes => /盗墓笔记8\s+/).each do |link|
    # puts link.href
    links[counter.to_s] = link.href
    counter = counter + 1
end

links.each do |key, value|
  
  puts(value, key)
  temp = agent.get(value).body
  
  hs = temp.match("<h1>")
  hsp = hs.begin(0)
  
  he = temp.match("</h1>")
  hep = he.begin(0)
    
  header = temp[hsp+"<h1>".length...hep]
  
  puts header
  
  marker1 = temp.match("<div class=\"content\">")
  start = marker1.begin(0) + "<div class=\"content\">".length
  
  marker2 = temp.match("<div style=\"padding:0px 0px 0px 10px; width:338px; float:left;\">")
  terminal = marker2.begin(0)
  
  #puts(temp[start...terminal])

  text = temp[start...terminal]
  text = text.delete("<p>")
  text = text.delete("</p>")
  text = text.delete('\n')
  
  file = File.open("ch" + key + ".txt", "w")
  file.puts(header + "\n")
  file.puts(text)
  file.close
  
  #break
  
end