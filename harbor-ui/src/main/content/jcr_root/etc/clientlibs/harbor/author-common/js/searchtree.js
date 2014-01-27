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

        DFsearch: function(searchTree, options){
            options = options || {};
            var searchConf = options.searchConfiguration;
            var toSearch = [];
            var searchResults = [];
            toSearch.push(searchTree.root);

            while(toSearch.length > 0){
                var currentNode = toSearch.pop();
                //If the current node has not already been visited
                if(!currentNode.visited){
                    currentNode.visited = true;

                    /*
                     Apply provided Search configuration logic.
                     */
                    //serachFunction decorates the node with a "context"
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

            }
            return searchResults;
        },

        defaultSearchConfig:function(design_content, target_name){
            var content = design_content;
            var name = target_name || "components";

            return {
                searchFunction: function(node){
                    var flag = false;
                    //TODO: clean this up
                    if(node.isRoot || (node.hasOwnProperty("content") == false)){
                        node.content = content;
                        return false;
                    }
                    //index into the content object using the node's search expression fragment
                    var obj = node.content[node.data];
                    node.content = obj;

                    /*
                     Found case: checks to see if obj exits,
                     and if obj has the property "name" (which is 'components')
                     */
                    if (obj && obj[name] != null) {
                        return true;
                    }
                    else{
                        return false;
                    }


/*
                    //this still needs to happen regardless if we found the components node in content or not
                    if(obj){
                        */
/*
                         Here the node's "search scope" is adjusted after successfully indexing into
                         the target content object, as the stuff we are looking for is buried
                         inside the content object. The structure of the content object will mirror the edge connections
                         in our search tree when a design exists for a particular cell.
                         *//*

                        //This will set the content level of *every node* where obj was successfully grabbed
                        node.content = obj;
                        //still return false, as we didn't actually find obj[name]
                    }
*/

                    //return flag;

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
        }
    }
}();