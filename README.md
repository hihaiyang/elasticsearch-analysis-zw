# Elasticsearch-analysis-zw
Analyzer：zhili，Tokenizer：zhili

## Versions
version|ES version
---|---:
7.0.0|7.0.0

## Guide
1.download

download from here：[v7.0.0.zip](https://github.com/hihaiyang/elasticsearch-analysis-zw.git)

**Quick Example**

2.指定分词器查看分词效果
```
POST _analyze
{
    "analyzer":"zhili",
    "text":"哈嘿呵"
}
```
Result
```
{
    "tokens":[
        {
	    "token":"哈",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	},{
	    "token":"哈嘿",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	},{
	    "token":"哈嘿呵",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	},{
	    "token":"嘿",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	},{
	    "token":"嘿呵",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	},{
	    "token":"呵",
	    "start_offset":0,
	    "end_offset":1,
	    "type":"word",
	    "position":0
	}
    ]
}
```