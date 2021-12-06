'use strict';

const threeByThreeHTML = '<div th:fragment="3x3-topster" id="3x3" class="topster-main col-12 col-lg-6">\n' +
    '<div class="row row-cols-3 g-2 g-lg-3 border border-dark">\n' +
    '<div id="position-1" th:value="1" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="1" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-2" th:value="2" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="2" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-3" th:value="3" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="3" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-4" th:value="4" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="4" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-5" th:value="5" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="5" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-6" th:value="6" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="6" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-7" th:value="7" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="7" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-8" th:value="8" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="8" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-9" th:value="9" name="position" class="col">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="9" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>';
const fourByFourHTML = '<div th:fragment="4x4-topster" id="4x4" class="topster-main col-12 col-lg-6">\n' +
    '<div class="row row-cols-4 g-2 g-lg-3 border border-dark">\n' +
    '<div id="position-1" th:value="1" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="1" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '\n' +
    '</div>\n' +
    '<div id="position-2" th:value="2" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="2" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-3" th:value="3" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="3" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-4" th:value="4" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="4" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-5" th:value="5" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="5" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-6" th:value="6" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="6" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-7" th:value="7" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="7" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-8" th:value="8" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="8" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-9" th:value="9" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="9" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-10" th:value="10" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="10" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-11" th:value="11" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="11" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-12" th:value="12" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="12" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-13" th:value="13" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="13" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-14" th:value="14" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="14" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-15" th:value="15" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="15" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-16" th:value="16" name="position" class="four-topster col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="16" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>';
const fiveByFiveHTML = '<div th:fragment="5x5-topster" id="5x5" class="topster-main col-12 col-lg-6">\n' +
    '<div class="row row-cols-5 g-2 g-lg-3 border border-dark">\n' +
    '<div id="position-1" th:value="1" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row Column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="1" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-2" th:value="2" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row Column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="2" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-3" th:value="3" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row Column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="3" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-4" th:value="4" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row Column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="4" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-5" th:value="5" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row Column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="5" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-6" th:value="6" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="6" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-7" th:value="7" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="7" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-8" th:value="8" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="8" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-9" th:value="9" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="9" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-10" th:value="10" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="10" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-11" th:value="11" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="11" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-12" th:value="12" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="12" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-13" th:value="13" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="13" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-14" th:value="14" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="14" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-15" th:value="15" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="15" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-16" th:value="16" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="16" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-17" th:value="17" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="17" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-18" th:value="18" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="18" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-19" th:value="19" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="19" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-20" th:value="20" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="20" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-21" th:value="21" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="21" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-22" th:value="22" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="22" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-23" th:value="23" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="23" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-24" th:value="24" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="24" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '<div id="position-25" th:value="25" name="position" class="col border border-dark">\n' +
    '<div class="p-3">Row column\n' +
    '<div class="form-group hide">\n' +
    '<input type="text" name="src[]">\n' +
    '<input type="text" name="title[]">\n' +
    '<input type="text" name="artist[]">\n' +
    '<input type="text" name="releaseDate[]">\n' +
    '<input type="text" name="spotifyID[]">\n' +
    '<input type="text" value="25" name="position[]">\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>\n' +
    '</div>';