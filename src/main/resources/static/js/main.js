var teacherLookup = function (query, done) {
    var settings = {
        "async": true,
        "method": "GET",
        "url": "/search-teachers?name=" + query
    };

    return $.ajax(settings).done(function (response) {
        var content = response.content
            .map(function (teacher) {
                teacher.value =teacher.account.fullName;
                return teacher;
            });

        var result = {
            suggestions: content
        };

        done(result);
    });
};

$('#teacher').autocomplete({
    lookup: teacherLookup,
    onSelect: function (teacher) {
        $("#teacher_id").val(teacher.id);
    }
});

var subjectLookup = function (query, done) {
    var settings = {
        "async": true,
        "method": "GET",
        "url": "/search-subjects?name=" + query
    };

    return $.ajax(settings).done(function (response) {
        var content = response.content
            .map(function (subject) {
                subject.value =subject.name;
                return subject;
            });

        var result = {
            suggestions: content
        };

        done(result);
    });
};

$('#subject').autocomplete({
    lookup: subjectLookup,
    onSelect: function (subject) {
        $("#subject_id").val(subject.id);
    }
});