Harbor.Overrides.SearchTree = function(){

    function Node(options){
        if(! (this instanceof Node))
            return new Node(options);

        options = options || {};
        this.isRoot = options.isRoot || false;
        this.data = options.expression || {};
        this.children = options.children || [];
    }

    function SearchTree(node){
        //Force the 'new' keyword when creating a search tree
        if(! (this instanceof SearchTree))
            return new SearchTree(node);

        this.root = node;
    }

    return {
        buildSearchTree: function(path){
            var subExpressions = path.split("/");
            var root = new Node({isRoot:true});
            var priorDepth = [];
            priorDepth.push(root);

            for(var i = 0; i < subExpressions.length; i++){
                var currentDepth = [];
                var curSubSplit = subExpressions[i].split("|");

                for(var j = 0; j < priorDepth.length; j++){
                    //loop through the split sub expressions in reverse
                    for(var k = curSubSplit.length - 1; k >= 0; k--){
                    //for(var k = 0; k < curSubSplit.length; k++){
                        var expressionNode = new Node({expression: curSubSplit[k]});
                        currentDepth.push(expressionNode);
                        priorDepth[j].children.push(expressionNode);
                    }
                }
                priorDepth = currentDepth;
            }
            return new SearchTree(root);
        },

        search: function(searchTree, options){
            options = options || {};
            var searchConf = options.searchConfiguration;
            var toSearch = [];
            var searchResults = [];
            toSearch.push(searchTree.root);

            while(toSearch.length > 0){
                var currentNode = toSearch.shift();

                /*
                    Apply provided Search configuration logic.
                 */
                if(searchConf.searchFunction(currentNode)){
                    if (searchConf.searchResultBuilder instanceof Function){
                        searchResults.push(searchConf.searchResultBuilder(currentNode));
                    }
                }
                if((!searchConf.pruneBranch instanceof Function) || searchConf.pruneBranch(currentNode) == false){
                    //transform node, add children
                    if(searchConf.transformNode instanceof Function){
                        //apply transform to
                        currentNode = searchConf.transformNode(currentNode);
                    }
                    for(var i = 0; i < currentNode.children.length; i++){
                        toSearch.push(currentNode.children[i]);
                    }
                }

            }
            return searchResults;
        }
    }
}();


//timing test
/*(function(){
    var func = CQ.wcm.Design.prototype.getStyleProperty
    CQ.wcm.Design.prototype.getStyleProperty = function(cell, name){
        console.time("LINEAR SEARCH TIMER");
        func(cell, name);
        console.timeEnd("LINEAR SEARCH TIMER");
    }
})();*/

/*
    Commence actual overrides of CQ machinery
*/
(function (){
    CQ.wcm.Design.prototype.getStyleProperty = function (cell, name) {
        console.time("TREE SEARCH");
        //MATT IS THE BEST, PAUL TOO
        if (this.content == null) {
            return null;
        }
        //scope access purposes
        var content = this.content;
        // check if name is a relpath
        var suffix = "";
        var idx = name.lastIndexOf('/');
        if (idx > 0)  {
            suffix = "/" + name.substring(0, idx);
            name = name.substring(idx + 1);
        }

        //Build Search Tree using the cell's search expression
        var searchTree = Harbor.Overrides.SearchTree.buildSearchTree(cell.searchExpr);

        //Grab style via a search over our tree.
        var style =  Harbor.Overrides.SearchTree.search(searchTree, {
            searchConfiguration: function(){
                return {
                    searchFunction: function(node){
                        //TODO: clean this up
                        if(node.isRoot || (node.hasOwnProperty("content") == false)){
                            node.content = content;
                            return false;
                        }
                        //index into the content object using the node's search expression fragment
                        var obj = node.content[node.data];
                        node.content = obj;

                       if(obj && (!obj.hasOwnProperty(name))){
                            /*
    `                       Here the node's "search scope" is adjusted after successfully indexing into
                            the target content object, as the stuff we are looking for is buried
                            inside the content object. The structure of the content object will mirror the edge connections
                            in our search tree when a design exists for a particular cell.*/

                            //This will set the content level of *every node* where obj was successfully grabbed
                            node.content = obj;
                            //still return false, as we didn't actually find obj[name]
                            return false;
                        }

                        /*
                            Found case: checks to see if obj exits,
                            and if obj has the property "name" (which is 'components')
                        */
                        if (obj && obj[name] != null) {
                            if(node.children.length == 0){
                                return true;
                            }
                            else{
                                //not done yet
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    },
                    searchResultBuilder: function(node){
                        var obj = node.content[name];
                        return obj;
                    },
                    pruneBranch: function(node){
                        if(node.isRoot){
                            return false;
                        }
                        //var obj = node.content[node.data];
                        if(!node.content){
                            return true;
                        }
                        else{
                            return false;
                        }
                    },
                    transformNode: function(node){
                        for(var i = 0; i < node.children.length; i++){
                            node.children[i].content = node.content;
                        }
                        return node;
                    }
                }
            }()
        });
        console.timeEnd("TREE SEARCH");
        return style[0];
    }
})();
