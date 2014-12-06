config = require "./config.coffee"

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')

create = ->
  console.log ':create'

  game.stage.backgroundColor = config.backgroundColor

  paddle = game.add.sprite(40, 300, 'paddle')
  paddle.scale.setTo(8, 8)

update = ->
  # TODO update

game = new Phaser.Game 1080, 600, Phaser.AUTO, '', preload: preload, create: create, update: update
