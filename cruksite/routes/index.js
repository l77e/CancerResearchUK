var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Cancer Research UK - Event Check In' });
});

router.get('/sign-in', function(req, res, next) {
  res.render('sign-in', { title: 'Cancer Research UK - Sign In' });
});

router.get('/sign-up', function(req, res, next) {
  res.render('sign-up', { title: 'Cancer Research UK - Sign Up' });
});


router.post('/submit-vol-sign-in', function(req, res, next) {
  //res.render('sign-up', { title: 'Cancer Research UK - Sign Up' });
});

module.exports = router;
