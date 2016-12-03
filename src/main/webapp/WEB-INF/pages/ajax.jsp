<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jQuery UI Draggable - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <style>
        #gallery { float: left; width: 65%; min-height: 12em; border: solid}
        .gallery.custom-state-active { background: #eee; }
        .gallery li { float: left; width: 96px; padding: 0.4em; margin: 0 0.4em 0.4em 0; text-align: center; border:solid}
        .gallery li h5 { margin: 0 0 0.4em; cursor: move; }
        .gallery li a { float: right; }
        .gallery li a.ui-icon-zoomin { float: left; }
        .gallery li img { width: 100%; cursor: move; }

        .card { float: left; width: 65%; min-height: 12em; border: solid}
        .card.custom-state-active { background: #eee; }
        .card li { float: left; width: 96px; padding: 0.4em; margin: 0 0.4em 0.4em 0; text-align: center; border:solid}
        .card li h5 { margin: 0 0 0.4em; cursor: move; }
        .card li a { float: right; }
        .card li a.ui-icon-zoomin { float: left; }
        .card li img { width: 100%; cursor: move; }

    </style>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>

<div class="ui-widget ui-helper-clearfix">

    <ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix">
        <li class="ui-widget-content ui-corner-tr">
            <h5 class="ui-widget-header">High Tatras</h5>
            <from id="form" action="/ajaxCall">
                <label>Lead 1</label>
                <input type="hidden" name="cardId" value="2" id="cardId">
                <input type="hidden" name="pt" value="1" id="pt">
                <input type="hidden" id="from" name="from" value="0">
            </from>
        </li>
        <li class="ui-widget-content ui-corner-tr">
            <h5 class="ui-widget-header">High Tatras 2</h5>
            <from id="form" action="/ajaxCall">
                <label>Lead 2</label>
                <input type="hidden" name="cardId" value="4" id="cardId">
                <input type="hidden" name="pt" value="1" id="pt">
                <input type="hidden" id="from" name="from" value="0">
            </from>
        </li>
        <li class="ui-widget-content ui-corner-tr">
            <h5 class="ui-widget-header">High Tatras 3</h5>
            <from id="form" action="/ajaxCall">
                <label>Lead 3</label>
                <input type="hidden" name="cardId" value="1" id="cardId">
                <input type="hidden" name="pt" value="1" id="pt">
                <input type="hidden" id="from" name="from" value="0">
            </from>
        </li>
        <li class="ui-widget-content ui-corner-tr">
            <h5 class="ui-widget-header">High Tatras 4</h5>

            <from id="form" action="/ajaxCall">
                <label>Lead 4</label>
                <input type="hidden" name="cardId" value="5" id="cardId">
                <input type="hidden" name="pt" value="1" id="pt">
                <input type="hidden" id="from" name="from" value="0">
            </from>
        </li>
    </ul>
</div>

<div id="card" class="card ui-widget-content ui-state-default">
    <input type="hidden" id="from" name="destination" value="1">
    <h4 class="ui-widget-header"><span class="ui-icon ui-icon-card1">Card1</span></h4>
    <ul class="gallery ui-helper-reset"> </ul>
</div>

<div id="card2" class="card ui-widget-content ui-state-default">
    <input type="hidden" id="from" name="destination" value="2">
    <h4 class="ui-widget-header"><span class="ui-icon ui-icon-card1">Card2</span></h4>
    <ul class="gallery ui-helper-reset"> </ul>
</div>


<script>
    $(document).ready(function(e) {

        $( function() {

            // There's the gallery and the card1
            var $gallery = $( "#gallery" ),
                $card1 = $( "#card" ), $card2 = $( "#card2" );

            // Let the gallery items be draggable
            $( "li", $gallery ).draggable({
                cancel: "a.ui-icon", // clicking an icon won't initiate dragging
                revert: "invalid", // when not dropped, the item will revert back to its initial position
                containment: "document",
                helper: "clone",
                cursor: "move"
            });

            // Let the card1 be droppable, accepting the gallery items
            $(".card").droppable({
                accept: "#gallery > li, .card li",
                classes: {
                    "ui-droppable-active": "ui-state-highlight"
                },
                drop: function( event, ui ) {
                    movePerson( ui.draggable, $(event.target) );
                    var $item = ui.draggable;
                    var $form = $($item).find("#form");
                    var cardId =  "cardId" + $($form).find("#cardId").val();

                    var $target = $(event.target);
                    var destination = $($target).find("#from").val();
                    var from = $($form).find("#from").val();
                    $($form).find("#from").attr("value", destination);

                    var json = { "destination" : destination, "from" : from};
                    //alert("dest = " + destination + ". from = " + from);

                    if(destination != from) {
                        $.ajax({
                            url: '/moveLead',
                            dataType: 'json',
                            type: 'POST',
                            data: JSON.stringify(json),
                            contentType: 'application/json',

                            success: function (data) {
                                //alert(data.value);
                            }
                        });
                    }
                }
            });


            // Let the gallery be droppable as well, accepting items from the card1
            $gallery.droppable({
                accept: ".card li",
                classes: {
                    "ui-droppable-active": "custom-state-active"
                },
                drop: function( event, ui ) {
                    movePerson( ui.draggable, $(event.target) );
                }
            });

            // Lead deletion function
            function movePerson( $item, $target ) {
                $item.appendTo( $target ).fadeIn();
            }



        });// function

    }); // document

</script>
</body>
</html>