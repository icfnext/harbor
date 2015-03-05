<%@include file="/libs/harbor/components/global.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <bedrock:title />
        <cq:includeClientLib categories="jquery"/>
        <cq:includeClientLib categories="harbor.bootstrap.default" />
        <cq:includeClientLib categories="harbor.bootstrapdesign.3.3.2" />
    </head>
    <body>

    <%@include file="form.html" %>

        <script type="text/javascript">

            $( document ).ready( function() {

                $.get( "${currentPage.name}/_jcr_content/brandproperties.1.json", function( data ) {

                    for ( var currentKey in data ) {
                        $( "input[name='" + currentKey + "']" ).val( data[ currentKey ] );
                    }

                } );

                $( "#bootstrapform" ).submit( function( event ) {

                    $.post( "${currentPage.name}/_jcr_content/brandproperties", $( "#bootstrapform" ).serialize() );
                    return false;

                } );

                $( ".save" ).click( function( event ) {

                    $.post( "${currentPage.name}/_jcr_content/brandproperties", $( "#bootstrapform" ).serialize() );
                    return false;

                } );

            } );

        </script>
    </body>
</html>