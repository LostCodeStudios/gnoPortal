Wall = (game, horizontal, x, y) ->
  this.horizontal = horizontal

  if (horizontal)
    this.sprite = game.add.sprite(x, y, 'wall-horizontal')
  else
    this.sprite = game.add.sprite(x, y, 'wall-vertical')

  this.sprite.wall = this # bad circular referencing!

  game.physics.arcade.enable(this.sprite)

  this.sprite.body.immovable = true
  this.sprite.body.moves = false # don't move! You're a wall!

module.exports = Wall
