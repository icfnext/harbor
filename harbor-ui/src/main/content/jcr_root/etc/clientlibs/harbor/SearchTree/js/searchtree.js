Harbor.Overrides.SearchTree = function(){

    function Node(options){
        if(! (this instanceof Node))
            return new Node(options);

        options = options || {};
        this.expression = options.expression || {};
        this.children = options.children || [];
    }


    var defaultSearchConfiguration = function(){

    }();

    function SearchTree(node){
        //Force the 'new' keyword when creating a search tree
        if(! (this instanceof SearchTree))
            return new SearchTree(node);

        this.root = node;
    }

    function buildSearchTree(path){
        var subExpressions = path.split("/");
        var root = new Node();
        var priorDepth = []
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
    }


    return {
        SearchTree: function SearchTree(path){
            //Force the 'new' keyword when creating a search tree
            if(! (this instanceof SearchTree))
                return new SearchTree(path);

            //instance variables
            this.path = path;

            this.tree = buildSearchTree(this.path);

        },






    }
}();