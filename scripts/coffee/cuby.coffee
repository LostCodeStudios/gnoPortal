config = require "./config.coffee"

Cuby = (game, pos) ->
  this.pos = pos;

  this.sprite = game.add.sprite(pos.x, pos.y, 'cuby')
  this.sprite.scale.setTo(config.spriteScale, config.spriteScale)

  game.physics.arcade.enable(this.sprite)

  this.sprite.body.velocity = new Phaser.Point(0, 0); # don't move by default

  this.fire = (direction) ->
    console.log "cuby fired"
    this.sprite.body.velocity = direction; # shoot it!

  this.trail = (color, time) ->
    console.log "should emit"

  return this

module.exports = Cuby
