Harbor.Overrides.SearchTree = function(){

    function Node(options){
        if(! (this instanceof Node))
            return new Node(options);

        options = options || {};
        this.expression = options.expression || {};
        this.children = options.children || [];
    }


    var defaultSearchConfiguration = function(){
        return {
            searchFunction: function(node){

            },
            searchResultBuilder: function(node){
                return node;
            },
            pruneBranch: function(node){

            }
        }
    }();

    function SearchTree(node){
        //Force the 'new' keyword when creating a search tree
        if(! (this instanceof SearchTree))
            return new SearchTree(node);

        this.root = node;
    }

    return {
        buildSearchTree: function(path){
            var subExpressions = path.split("/");
            var root = new Node();
            var priorDepth = [];
            priorDepth.push(root);

            for(var i = 0; i < subExpressions.length; i++){
                var currentDepth = [];
                var curSubSplit = subExpressions[i].split("|");

                for(var j = 0; j < priorDepth.length; j++){
                    //loop through the split sub expressions in reverse
                    for(var k = curSubSplit.length - 1; k >= 0; k--){
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
            var searchConf = options.searchConfiguration || defaultSearchConfiguration;
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
                    for(var i = 0; i < currentNode.children.length; i++){
                        toSearch.push(currentNode.children[i]);
                    }
                }

            }

        }

    }
}();