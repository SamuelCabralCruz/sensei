# Sensei - Code Review Plugin for JetBrains IDEs

## List of features

- Available for all [Jetbrains IDEs](https://www.jetbrains.com/products.html#type=ide).
- Support all languages.

## Limitations

- Report highlighting might not work if language fall of this list.
- Line markers in the IDE might be misplaced depending on the granularity of the PSI elements. 

## Code Highlighting

- We use [prism](https://prismjs.com) in order to highlight code snippets.
- In order to update the scripts (`prism.css` and `prism.js`), simply go to the following [page](https://prismjs.com/download).
    - Here are the options we currently use:
        - Compression: Minified version 
        - Theme: Coy
        - Languages: All
        - Plugins: 
            - Line Highlight
            - Line Numbers
            - Normalize whitespace
            - Toolbar
            - Copy to Clipboard Button    
- We also customize the exported stylesheet.
    - Remove the pseudo-elements on the pre tag
        ```css
        pre[class*="language-"]:before,
        pre[class*="language-"]:after {
        	content: '';
        	z-index: -2;
        	display: block;
        	position: absolute;
        	bottom: 0.75em;
        	left: 0.18em;
        	width: 40%;
        	height: 20%;
        	max-height: 13em;
        	box-shadow: 0px 13px 8px #979797;
        	-webkit-transform: rotate(-2deg);
        	-moz-transform: rotate(-2deg);
        	-ms-transform: rotate(-2deg);
        	-o-transform: rotate(-2deg);
        	transform: rotate(-2deg);
        }
        ```
    - Remove line highlight gradient
        ```css
        .line-highlight {
            background: linear-gradient(to right, hsla(201, 100%, 50%, .1) 70%, hsla(201, 100%, 50%, 0));
        }
        ```
    - Modify the line highlighting color
        ```css
        .line-highlight {
            ...
            background: hsla(201, 100%, 50%, .08);
            ...
        }
        
        .line-highlight:before,
        .line-highlight[data-end]:after {
            ...
            background-color: hsla(201, 100%, 50%, 1);
            color: hsl(201, 100%, 95%);
            ...
        }
        ```
    - Remove vertical separator
        ```css
        pre[class*="language-"] > code {
            ...
            border-left: 10px solid #358ccb;
            box-shadow: -1px 0px 0px 0px #358ccb, 0px 0px 0px 1px #dfdfdf;
            ...
        }
        ``` 
    - Remove alternate line background
        ```css
        pre[class*="language-"] > code {
            ...
            background-image: linear-gradient(transparent 50%, rgba(69, 142, 209, 0.04) 50%);
            background-size: 3em 3em;
            background-origin: content-box;
            background-attachment: local;
            ...
        }
        ```
    - Remove right padding
        ```css
        code[class*="language"] {
            ...
            padding: 0 1em;
            ...
        }
        ```
    - Remove margin pre tag
        ```css
        pre[class*="language-"] {
            ...
            margin: 0;
            ...
        }
      
        /* Margin bottom to accommodate shadow */
        :not(pre) > code[class*="language-"],
        pre[class*="language-"] {
            background-color: #fdfdfd;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin-bottom: 1em;
        }
        ```
    - Modify code background color
        ```css
        pre[class*="language-"] > code {
            ... 
            background-color: #ededed;
            ...
        }
       ```
    - Remove tokens background color
        ```css
        .token.operator,
        .token.entity,
        .token.url,
        .token.variable {
            ...
            background: rgba(255, 255, 255, 0.5);
            ...
        }
        
        .language-css .token.string,
        .style .token.string {
            ...
            background: rgba(255, 255, 255, 0.5);
            ...
        }
        ```
    - Remove inline code related styling
        ```css
        /* Inline code */
        :not(pre) > code[class*="language-"] {
            position: relative;
            padding: .2em;
            border-radius: 0.3em;
            color: #c92c2c;
            border: 1px solid rgba(0, 0, 0, 0.1);
            display: inline;
            white-space: normal;
        }
        
        :not(pre) > code[class*="language-"]:after,
        pre[class*="language-"]:after {
            right: 0.75em;
            left: auto;
            -webkit-transform: rotate(2deg);
            -moz-transform: rotate(2deg);
            -ms-transform: rotate(2deg);
            -o-transform: rotate(2deg);
            transform: rotate(2deg);
        }
        ```
    - Hide overflow code block
        ```css
        code[class*="language"] {
            ...
            overflow: hidden;
        }
        ```

## JQuery

In order to use JQuery, we have to [download](https://code.jquery.com/jquery/) a minified version.
We are currently using the version [`jQuery Core 3.4.1`](https://code.jquery.com/jquery-3.4.1.min.js).
There was a mention to include `integrity` and `crossorigin` attributes to the script tag, but we did not.

## Favicon

Favicon images have been generated using [Real Favicon Generator](https://realfavicongenerator.net/).
