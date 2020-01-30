var express = require('express')
var routes = new express.Router()

routes.get('/', function (req, res) {
      res.sendFile('./CRUK-volunteer-landing-page.html', {root: __dirname + "/public"});
  })


  routes.get('/CRUK-volunteer-landing-page.html', function (req, res) {
    res.sendFile('./CRUK-volunteer-landing-page.html', {root: __dirname + "/public"});
})


  routes.get('/cruk-volunteer-sign-in-form.html', function (req, res) {
    res.sendFile('./cruk-volunteer-sign-in-form.html', {root: __dirname + "/public"});
})

routes.get('/cruk-volunteer-sign-up-form.html', function (req, res) {
    res.sendFile('./cruk-volunteer-sign-in-form.html', {root: __dirname + "/public"});
})

routes.get('/css/style.css', function (req, res) {
    res.sendFile('./css/style.css', {root: __dirname + "/public"});
})

  module.exports = routes