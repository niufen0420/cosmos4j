# Introduction #

刚刚讨论了很多关于页面静态化的帖子，在这里把Cosmos中关于页面静态化的解决方案贴出来，应该说这个方案能基本上解决上面帖子讨论的所有问题，包括分页。
第一步、在开发中，一定要将所有链接转化成静态链接，即：**.html的格式；
第二部、部署Cosmos中所附带的静态化的过滤器，部署方法如下：
>**

&lt;filter&gt;


> > 

&lt;display-name&gt;

static

&lt;/display-name&gt;


> > 

&lt;filter-name&gt;

static

&lt;/filter-name&gt;


> > 

&lt;filter-class&gt;

com.microbrain.cosmos.web.filters.StaticEnableFilter

&lt;/filter-class&gt;


> > 

&lt;init-param&gt;


> > > 

&lt;param-name&gt;

relative

&lt;/param-name&gt;


> > > 

&lt;param-value&gt;

false

&lt;/param-value&gt;



> > 

&lt;/init-param&gt;


> > 

&lt;init-param&gt;


> > > 

&lt;param-name&gt;

root

&lt;/param-name&gt;


> > > 

&lt;param-value&gt;

E:/Web/cosmos/

&lt;/param-value&gt;



> > 

&lt;/init-param&gt;


> > 

&lt;init-param&gt;


> > > 

&lt;param-name&gt;

encoding

&lt;/param-name&gt;


> > > 

&lt;param-value&gt;

UTF-8

&lt;/param-value&gt;



> > 

&lt;/init-param&gt;



> 

&lt;/filter&gt;


> 

&lt;filter-mapping&gt;


> > 

&lt;filter-name&gt;

static

&lt;/filter-name&gt;


> > 

&lt;url-pattern&gt;

/**&lt;/url-pattern&gt;



> 

&lt;/filter-mapping&gt;


这个过滤器包括三个参数，分别是relative、root以及encoding，relative是指产生的静态文件是否就在应用程序的相对目录里，如果是相对目录，则root参数则可以使用相对路径，root参数是指产生的静态文件所放置的根目录，而encoding是指产生的HTML文件的编码。
第三步、配置Apache，使其能够达到以下目标：检查静态文件是否存在，存在则给用户返回静态文件，如果不存在，则重写URL，导向用户到动态页面，并在动态页面中加入一个参数cosmosStaticFile=/index.html，这个参数的存在可以触发上面配置的过滤器，使该过滤器能够将输出的响应页面保存到root参数配置的根目录下的文件/index.html中，根据cosmosStaticFile值的不同，可以保存不同的静态页面，下面是一个URL重写的样例配置：
> > RewriteEngine On
> > RewriteLog  logs/cosmos-rewrite.log
> > RewriteCond %{DOCUMENT\_ROOT}%{REQUEST\_URI} !-f
> > RewriteRule <sup>/develop/([</sup>-]+)-local-commands.html$ /dev/domainLocalCommands?forward=development.localCommands&domain=$1&cosmosStaticFile=/develop/$1-local-commands.html [PT,QSA,L]
上面的配置中RewriteCond来判断当前访问的静态页面是否存在，不存在则执行下面的URL重写规则，在重写规则中除了普通的动态页面参数之外，增加了一个参数为cosmosStaticFile=/develop/$1-local-commands.html，这个参数将会触发上面的过滤器，将这个URL的响应写到/develop/$1-local-commands.html文件中去。
最后，通过上述步骤，已经基本上实现了动态页面自动静态化的过程了，现在唯一需要开发人员在开发中注意的是，如果一旦发布新的帖子，或者内容，需要通过程序去清理上面配置目录里的相关静态页面，从而在下次访问的时候，能重新产生该页面。
其实这个做法，在PHP开发人员中，似乎知道的比较多，Java这么做的人并不多，但实际上，利用Java的过滤器，比起PHP来说，方便太多了，而且，可以能达到良好的扩展性和低耦合度。**

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages