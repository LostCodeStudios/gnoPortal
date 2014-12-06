config = require "./config.coffee"
Paddle = require "./paddle.coffee"

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')

create = ->
  console.log ':create'

  game.stage.backgroundColor = config.backgroundColor

  paddle = new Paddle(game, config.colorCodes.blue)
  paddle2 = new Paddle(game, config.colorCodes.orange)

update = ->
  # TODO update

game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
