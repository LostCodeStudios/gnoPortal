config = require "./config.coffee"

Cuby = (game, pos) ->
  this.pos = pos;

  this.sprite = game.add.sprite(pos.x, pos.y, 'cuby')

  game.physics.arcade.enable(this.sprite)

  this.sprite.body.velocity = new Phaser.Point(0, 0); # don't move by default

  this.fire = (direction) ->
    console.log "cuby fired"
    direction.normalize()


    this.sprite.body.velocity = direction.setMagnitude(config.cubySpeed); # shoot it!

  this.trail = (color, time) ->
    console.log "should emit"

  this.switch = (isH) ->
    if isH then this.sprite.body.velocity.multiply(1,-1)
    else this.sprite.body.velocity.multiple(-1,1)


  return this

module.exports = Cuby
