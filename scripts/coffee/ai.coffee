config = require "./config.coffee"

AI = (game, paddle, cuby) ->
  this.onBallCollide = ->
    distance = paddle.sprite.position.x - cuby.sprite.position.x;
    time = distance/cuby.sprite.body.velocity.x;
    
    console.log(time);

  this.paddleUpdate = ->

  return this;

module.exports = AI