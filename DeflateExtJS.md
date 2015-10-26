针对配置了Apache的DEFLATE功能的ExtJS压缩比的测试

# Introduction #
由于ExtJS本身非常庞大，因此，考虑使用Apache的Deflate功能进行压缩，发现压缩比非常不错，通常能够
达到26%左右。也就是说原来500多KB的文本文件

# Details #
配置Apache的DEFLATE功能，压缩所有的JS文件和CSS文件还有HTML文件
压缩效果如下：
"GET /demo/demo.html HTTP/1.1" 359/668 (53%)
"GET /demo/ext-all.css HTTP/1.1" 13177/82604 (15%)
"GET /demo/ext-base.js HTTP/1.1" 11536/35849 (32%)
"GET /demo/ext-all.js HTTP/1.1" 143516/538956 (26%)

上述后面几列分别是：压缩后的大小/压缩前大小  (压缩比)

测试网址是：
http://www.fleashare.com/demo/demo.html
仅用ExtJS做了一个非常简单的菜单。