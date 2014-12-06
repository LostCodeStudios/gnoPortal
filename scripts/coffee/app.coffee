config = require "./config.coffee"
Paddle = require "./paddle.coffee"
Cuby = require "./cuby.coffee"

paddles = [ ]

upKey = { }
downKey = { }

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')
  game.load.image('cuby', 'assets/images/qb.png')

create = ->

  game.stage.backgroundColor = config.backgroundColor
  game.stage.smoothed = false

  game.physics.startSystem(Phaser.Physics.ARCADE)

  paddles[config.colorCodes.blue] = new Paddle(game, config.colorCodes.blue)
  paddles[config.colorCodes.orange] = new Paddle(game, config.colorCodes.orange)

  upKey = game.input.keyboard.addKey(Phaser.Keyboard.W)
  downKey = game.input.keyboard.addKey(Phaser.Keyboard.S)

  companion = new Cuby(this, new Phaser.Point(config.screenWidth/2, config.screenHeight/2))
  companion.fire(new Phaser.Point(109,100))
  setTimeout ->
    companion.switch(true)
  , 400




updateBlue = ->

  paddles[config.colorCodes.blue].update()

  # TODO handle input
  if upKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.up)

  if downKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.down)

updateOrange = ->

  paddles[config.colorCodes.blue].update

  # TODO run AI decisions

update = ->

  updateBlue()
  updateOrange()

game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
