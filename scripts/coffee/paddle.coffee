config = require "./config.coffee"

Paddle = (game, colorCode) ->
  this.colorCode = colorCode

  x = 0
  y = config.screenHeight / 2 - game.cache.getImage('paddle').height * config.spriteScale / 2

  switch (colorCode)
    when config.colorCodes.blue
      x = config.paddleMargin
    when config.colorCodes.orange
      x = config.screenWidth - config.paddleMargin - game.cache.getImage('paddle').width * config.spriteScale

  this.sprite = game.add.sprite(x, y, 'paddle')
  this.sprite.scale.setTo(config.spriteScale, config.spriteScale)

  game.physics.arcade.enable(this.sprite)

  this.move = (direction) ->
    console.log "moving"

    switch direction
      when config.dirCodes.up
        this.sprite.body.velocity = new Phaser.Point(0, -config.paddleSpeed)
      when config.dirCodes.down
        this.sprite.body.velocity = new Phaser.Point(0, config.paddleSpeed)

  this.update = ->
    this.sprite.body.velocity = new Phaser.Point(0, 0); # don't move by default

  return this

module.exports = Paddle
