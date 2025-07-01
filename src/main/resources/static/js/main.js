$(function() {
    $('.変更') .on('click', function() {
         if(!confirm('変更してよいですか？')){
            return false;
         }
    });

    $('.削除') .on('click', function() {
         if(!confirm('削除してよいですか？')){
            return false;
         }
    });
});
