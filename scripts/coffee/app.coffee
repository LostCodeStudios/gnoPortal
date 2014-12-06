config = require "./config.coffee"
Paddle = require "./paddle.coffee"

paddles = [ ]

upKey = { }
downKey = { }

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')

create = ->
  console.log ':create'

  game.stage.backgroundColor = config.backgroundColor

  paddles[config.colorCodes.blue] = new Paddle(game, config.colorCodes.blue)
  paddles[config.colorCodes.orange] = new Paddle(game, config.colorCodes.orange)

  upKey = game.input.keyboard.addKey(Phaser.Keyboard.W)
  downKey = game.input.keyboard.addKey(Phaser.Keyboard.S)

update = ->
  console.log ':update'

  updateBlue
  updateOrange

updateBlue = ->
  console.log ':updateBlue'

  paddles[config.colorCodes.blue].update

  # TODO handle input
  if upKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.up)

  if downKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.down)

updateOrange = ->
  console.log ":updateOrange"

  paddles[config.colorCodes.blue].update

  # TODO run AI decisions

game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
