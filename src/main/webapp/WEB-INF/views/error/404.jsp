<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
    <title>404</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Lato:400,100'>

    <style>
        @import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700&display=swap');

        *, *:before, *:after {
            box-sizing: border-box;
        }

        body {
            background: linear-gradient(135deg, #282430 0%, #100e12 100%);
            min-height: 100vh;
            font-family: 'Lato', 'Nanum Gothic', sans-serif;
        }

        .table {
            display: table;
            width: 100%;
        }

        .table-cell {
            display: table-cell;
            vertical-align: middle;
        }

        h1 {
            font-family: 'Lato', sans-serif;
            font-weight: 100;
            font-size: 40vmin;
            text-align: center;
            line-height: 30vh;
            margin: 6vh 0 10vh 0;
            color: rgba(255, 255, 255, 0.7);
            text-shadow: 1px 2px 5px rgba(0, 0, 0, 0.5);
        }

        h2 {
            font-weight: 100;
            color: rgba(255, 255, 255, 0.7);
            text-align: center;
            line-height: 1.5em;
            font-size: 2.5vmin;
            font-family: 'Nanum Gothic';
        }

        .mainbox {
            height: 100vmin;
        }

        header {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            padding-top: 30px;
        }

        .holder {
            width: 100%;
            padding: 20px;
            margin: 0 auto;
            text-align: center;
        }

        .container {
            margin: 0 auto;
            width: 100%;
            max-width: 1190px;
            position: relative;
            padding: 0 30px;
        }

        button {
            mix-blend-mode: screen;
            -webkit-appearance: none;
            outline: none;
            border: 2px solid #fff;
            background: transparent;
            color: #fff;
            padding: 20px 60px;
            font-size: 2vmin;
            letter-spacing: 0.25vmin;
            margin: 3vh 0;
            -webkit-transition: all 0.2s;
            transition: all 0.2s;
            opacity: 0.85;
        }

        button:hover {
            background-color: #fff;
            color: #000;
        }

        .tl-logo {
            opacity: 0.7;
            mix-blend-mode: screen;
            position: relative;
            font-size: 60px;
            width: 1em;
            height: 1em;
            background-color: #fff;
            border-radius: 100%;
            cursor: pointer;
        }

        .tl-logo:before, .tl-logo:after {
            position: absolute;
            display: block;
            content: '';
            height: 0;
            width: 0.28em;
            border: 0.12em solid #000;
            border-left: 0;
            border-bottom: 0;
            box-sizing: border-box;
            top: 50%;
            margin-top: -0.18em;
            left: 50%;
            margin-left: -0.3em;
            -webkit-transform: scale(0, 1);
            transform: scale(0, 1);
            -webkit-transform-origin: 0% 0%;
            transform-origin: 0% 0%;
            -webkit-transition: height 0.2s ease-in, -webkit-transform 0.1s 0.2s ease-out;
            transition: height 0.2s ease-in, -webkit-transform 0.1s 0.2s ease-out;
            transition: height 0.2s ease-in, transform 0.1s 0.2s ease-out;
            transition: height 0.2s ease-in, transform 0.1s 0.2s ease-out, -webkit-transform 0.1s 0.2s ease-out;
        }

        .tl-logo:after {
            border: 0.12em solid #000;
            border-right: 0;
            border-top: 0;
            left: auto;
            right: 50%;
            top: auto;
            bottom: 50%;
            margin-bottom: -0.18em;
            margin-right: -0.3em;
            -webkit-transform-origin: 100% 100%;
            transform-origin: 100% 100%;
        }

        .tl-logo.hover:before, .tl-logo.hover:after {
            height: 0.36em;
            -webkit-transform: scale(1, 1);
            transform: scale(1, 1);
            -webkit-transition: height 0.2s 0.1s ease-out, -webkit-transform 0.1s ease-in;
            transition: height 0.2s 0.1s ease-out, -webkit-transform 0.1s ease-in;
            transition: height 0.2s 0.1s ease-out, transform 0.1s ease-in;
            transition: height 0.2s 0.1s ease-out, transform 0.1s ease-in, -webkit-transform 0.1s ease-in;
        }

        .tl-logo.hover {
            -webkit-transform: rotate(180deg);
            transform: rotate(180deg);
            -webkit-transition: -webkit-transform 0.5s ease;
            transition: -webkit-transform 0.5s ease;
            transition: transform 0.5s ease;
            transition: transform 0.5s ease, -webkit-transform 0.5s ease;
        }
    </style>
</head>
<body>
<!-- partial:index.partial.html -->
<header>
    <div class="container">
        <div id="logo" class="tl-logo"></div>
    </div>
</header>
<div class="table mainbox">
    <div class="table-cell">
        <div class="holder">
            <h2>Page not found</h2>
            <h1>404</h1>
            <h2>죄송합니다. 페이지를 찾을수 없습니다.</h2>
            <a href="<%=request.getContextPath()%>/">
                <button>GO HOME</button>
            </a>
        </div>
    </div>
</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js'></script>

<script>
    $(function () {
        setTimeout(function () {
            $('#logo').addClass('hover');
        }, 300);
    });
</script>
</body>
</html>
