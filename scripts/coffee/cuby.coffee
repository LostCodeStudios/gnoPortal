config = require "./config.coffee"

Cuby = (game, pos) ->
  this.pos = pos

  this.sprite = game.add.sprite(pos.x, pos.y, 'cuby')
  this.sprite.scale.setTo(2, 2)
  this.sprite.smoothed = false

  game.physics.arcade.enable(this.sprite)

  this.sprite.body.velocity = new Phaser.Point(0, 0); # don't move by default

  this.sprite.body.bounce.x = 1.1 # slight increase of speed
  this.sprite.body.bounce.y = 1.1 # slight increase of speed
  this.sprite.body.maxVelocity = 1000 # TODO calibrate

  this.fire = (direction) ->
    console.log "cuby fired"
    direction.normalize()

    this.sprite.body.velocity = direction.setMagnitude(config.cubySpeed); # shoot it!

  this.trail = (color, time) ->
    console.log "should emit"

  this.events = [ ]

  this.onCollide = ->
    i = 0
    while i < this.events.length
      this.events[i]()
      ++i

  this.addEvent = (callback) ->
    this.events[this.events.length] = callback

  return this

module.exports = Cuby
