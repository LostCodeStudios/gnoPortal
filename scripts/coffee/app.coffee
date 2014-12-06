config = require "./config.coffee"
Paddle = require "./paddle.coffee"

paddles = [ ]

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')

create = ->
  console.log ':create'

  game.stage.backgroundColor = config.backgroundColor

  game.physics.startSystem(Phaser.Physics.ARCADE)

  paddles[config.colorCodes.blue] = new Paddle(game, config.colorCodes.blue)
  paddles[config.colorCodes.orange] = new Paddle(game, config.colorCodes.orange)

update = ->
  updateBlue
  updateOrange

updateBlue = ->
  paddles[config.colorCodes.blue].update

  # TODO handle input

updateOrange = ->
  paddles[config.colorCodes.blue].update

  # TODO run AI decisions

game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
