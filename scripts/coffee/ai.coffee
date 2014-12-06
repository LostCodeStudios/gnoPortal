config = require "./config.coffee"

target = null;
AI = (game, paddle, cuby) ->

  this.onBallCollide = ->
    cv = cuby.sprite.body.velocity;
    cp = cuby.sprite.position;
    pv = paddle.sprite.body.velocity;
    pp = paddle.sprite.position;

    distance = pp.x- cp.x;
    time = distance/cv.x;

    console.log(distance);
    console.log(time);
    if Math.abs(time*cv.y) > time*config.paddleSpeed
      console.log("shit") 
    else
      target = new Phaser.Point(pp.x, pp.y + time*cv.y)
      console.log(target)
     

  this.paddleUpdate = ->
    if target != null
      console.log("moving towards")
      dir = Phaser.Point.subtract(target,paddle.sprite.position);
      if dir.getMagnitude() < 300
        this.target = null
        paddle.sprite.body.velocity = new Phaser.Point(0,0)
      else
        if dir.y < 0
          paddle.move config.dirCodes.up
        else
          paddle.move config.dirCodes.down

  return this;

module.exports = AI