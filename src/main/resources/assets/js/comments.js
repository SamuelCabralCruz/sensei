function fileCommentHoverIn() {
    return function () {
        console.log("file-comment hover in");
        $(document).trigger('resize');
    };
}

function fileCommentHoverOut() {
    return function () {
        console.log("file-comment hover out");
    };
}

$(document).ready(function () {
    $('div.file-comment').each(function () {
        $(this).hover(fileCommentHoverIn(), fileCommentHoverOut());
    })
});
