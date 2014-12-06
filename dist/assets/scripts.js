(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var collectStar, create, cursors, game, platforms, player, preload, score, scoreText, stars, update;

console.log('hello fag');

preload = function() {
  console.log(':preload');
  game.load.image('sky', '/assets/images/sky.png');
  game.load.image('ground', '/assets/images/platform.png');
  game.load.image('star', '/assets/images/star.png');
  return game.load.spritesheet('dude', '/assets/images/dude.png', 32, 48);
};

platforms = null;

player = null;

cursors = null;

stars = null;

scoreText = null;

score = 0;

create = function() {
  var ground, i, ledge, star, _i;
  console.log(':create');
  game.add.sprite(0, 0, 'sky');
  platforms = game.add.group();
  ground = platforms.create(0, game.world.height - 64, 'ground');
  ground.scale.setTo(2, 2);
  ground.body.immovable = true;
  ledge = platforms.create(400, 400, 'ground');
  ledge.body.immovable = true;
  ledge = platforms.create(-150, 250, 'ground');
  ledge.body.immovable = true;
  game.add.sprite(0, 0, 'star');
  player = game.add.sprite(32, game.world.height - 150, 'dude');
  player.body.bounce.y = 0.2;
  player.body.gravity.y = 6;
  player.body.collideWorldBounds = true;
  player.animations.add('left', [0, 1, 2, 3], 10, true);
  player.animations.add('right', [5, 6, 7, 8], 10, true);
  cursors = game.input.keyboard.createCursorKeys();
  stars = game.add.group();
  for (i = _i = 0; _i <= 12; i = ++_i) {
    star = stars.create(i * 70, 0, 'star');
    star.body.gravity.y = 6;
    star.body.bounce.y = 0.7 + Math.random() * 0.2;
  }
  return scoreText = game.add.text(16, 16, 'score: 0', {
    font: '32px arial',
    fill: '#000'
  });
};

collectStar = function(player, star) {
  star.kill();
  score += 10;
  return scoreText.content = "Score: " + score;
};

update = function() {
  game.physics.collide(player, platforms);
  game.physics.collide(stars, platforms);
  game.physics.overlap(player, stars, collectStar, null, this);
  player.body.velocity.x = 0;
  if (cursors.left.isDown) {
    player.body.velocity.x = -150;
    player.animations.play('left');
  } else if (cursors.right.isDown) {
    player.body.velocity.x = 150;
    player.animations.play('right');
  } else {
    player.animations.stop();
    player.frame = 4;
  }
  if (cursors.up.isDown && player.body.touching.down) {
    return player.body.velocity.y = -350;
  }
};

game = new Phaser.Game(800, 600, Phaser.AUTO, '', {
  preload: preload,
  create: create,
  update: update
});


},{}]},{},[1])
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL3dpbGxpYW0vZGV2L2xvc3Rjb2RlL2dub1BvcnRhbC9ub2RlX21vZHVsZXMvZ3VscC1icm93c2VyaWZ5L25vZGVfbW9kdWxlcy9icm93c2VyaWZ5L25vZGVfbW9kdWxlcy9icm93c2VyLXBhY2svX3ByZWx1ZGUuanMiLCIvaG9tZS93aWxsaWFtL2Rldi9sb3N0Y29kZS9nbm9Qb3J0YWwvc2NyaXB0cy9jb2ZmZWUvYXBwLmNvZmZlZSJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtBQ0FBLElBQUEsK0ZBQUE7O0FBQUEsT0FBTyxDQUFDLEdBQVIsQ0FBWSxXQUFaLENBQUEsQ0FBQTs7QUFBQSxPQUVBLEdBQVUsU0FBQSxHQUFBO0FBQ1IsRUFBQSxPQUFPLENBQUMsR0FBUixDQUFZLFVBQVosQ0FBQSxDQUFBO0FBQUEsRUFDQSxJQUFJLENBQUMsSUFBSSxDQUFDLEtBQVYsQ0FBZ0IsS0FBaEIsRUFBdUIsd0JBQXZCLENBREEsQ0FBQTtBQUFBLEVBRUEsSUFBSSxDQUFDLElBQUksQ0FBQyxLQUFWLENBQWdCLFFBQWhCLEVBQTBCLDZCQUExQixDQUZBLENBQUE7QUFBQSxFQUdBLElBQUksQ0FBQyxJQUFJLENBQUMsS0FBVixDQUFnQixNQUFoQixFQUF3Qix5QkFBeEIsQ0FIQSxDQUFBO1NBSUEsSUFBSSxDQUFDLElBQUksQ0FBQyxXQUFWLENBQXNCLE1BQXRCLEVBQThCLHlCQUE5QixFQUF5RCxFQUF6RCxFQUE2RCxFQUE3RCxFQUxRO0FBQUEsQ0FGVixDQUFBOztBQUFBLFNBU0EsR0FBWSxJQVRaLENBQUE7O0FBQUEsTUFVQSxHQUFTLElBVlQsQ0FBQTs7QUFBQSxPQVdBLEdBQVUsSUFYVixDQUFBOztBQUFBLEtBWUEsR0FBUSxJQVpSLENBQUE7O0FBQUEsU0FhQSxHQUFZLElBYlosQ0FBQTs7QUFBQSxLQWNBLEdBQVEsQ0FkUixDQUFBOztBQUFBLE1BZ0JBLEdBQVMsU0FBQSxHQUFBO0FBQ1AsTUFBQSwwQkFBQTtBQUFBLEVBQUEsT0FBTyxDQUFDLEdBQVIsQ0FBWSxTQUFaLENBQUEsQ0FBQTtBQUFBLEVBQ0EsSUFBSSxDQUFDLEdBQUcsQ0FBQyxNQUFULENBQWdCLENBQWhCLEVBQW1CLENBQW5CLEVBQXNCLEtBQXRCLENBREEsQ0FBQTtBQUFBLEVBRUEsU0FBQSxHQUFZLElBQUksQ0FBQyxHQUFHLENBQUMsS0FBVCxDQUFBLENBRlosQ0FBQTtBQUFBLEVBR0EsTUFBQSxHQUFTLFNBQVMsQ0FBQyxNQUFWLENBQWlCLENBQWpCLEVBQW9CLElBQUksQ0FBQyxLQUFLLENBQUMsTUFBWCxHQUFvQixFQUF4QyxFQUE0QyxRQUE1QyxDQUhULENBQUE7QUFBQSxFQUlBLE1BQU0sQ0FBQyxLQUFLLENBQUMsS0FBYixDQUFtQixDQUFuQixFQUFzQixDQUF0QixDQUpBLENBQUE7QUFBQSxFQUtBLE1BQU0sQ0FBQyxJQUFJLENBQUMsU0FBWixHQUF3QixJQUx4QixDQUFBO0FBQUEsRUFPQSxLQUFBLEdBQVEsU0FBUyxDQUFDLE1BQVYsQ0FBaUIsR0FBakIsRUFBc0IsR0FBdEIsRUFBMkIsUUFBM0IsQ0FQUixDQUFBO0FBQUEsRUFRQSxLQUFLLENBQUMsSUFBSSxDQUFDLFNBQVgsR0FBdUIsSUFSdkIsQ0FBQTtBQUFBLEVBVUEsS0FBQSxHQUFRLFNBQVMsQ0FBQyxNQUFWLENBQWlCLENBQUEsR0FBakIsRUFBdUIsR0FBdkIsRUFBNEIsUUFBNUIsQ0FWUixDQUFBO0FBQUEsRUFXQSxLQUFLLENBQUMsSUFBSSxDQUFDLFNBQVgsR0FBdUIsSUFYdkIsQ0FBQTtBQUFBLEVBYUEsSUFBSSxDQUFDLEdBQUcsQ0FBQyxNQUFULENBQWdCLENBQWhCLEVBQW1CLENBQW5CLEVBQXNCLE1BQXRCLENBYkEsQ0FBQTtBQUFBLEVBZUEsTUFBQSxHQUFTLElBQUksQ0FBQyxHQUFHLENBQUMsTUFBVCxDQUFnQixFQUFoQixFQUFvQixJQUFJLENBQUMsS0FBSyxDQUFDLE1BQVgsR0FBb0IsR0FBeEMsRUFBNkMsTUFBN0MsQ0FmVCxDQUFBO0FBQUEsRUFpQkEsTUFBTSxDQUFDLElBQUksQ0FBQyxNQUFNLENBQUMsQ0FBbkIsR0FBdUIsR0FqQnZCLENBQUE7QUFBQSxFQWtCQSxNQUFNLENBQUMsSUFBSSxDQUFDLE9BQU8sQ0FBQyxDQUFwQixHQUF3QixDQWxCeEIsQ0FBQTtBQUFBLEVBbUJBLE1BQU0sQ0FBQyxJQUFJLENBQUMsa0JBQVosR0FBaUMsSUFuQmpDLENBQUE7QUFBQSxFQXFCQSxNQUFNLENBQUMsVUFBVSxDQUFDLEdBQWxCLENBQXNCLE1BQXRCLEVBQThCLFlBQTlCLEVBQXNDLEVBQXRDLEVBQTBDLElBQTFDLENBckJBLENBQUE7QUFBQSxFQXNCQSxNQUFNLENBQUMsVUFBVSxDQUFDLEdBQWxCLENBQXNCLE9BQXRCLEVBQStCLFlBQS9CLEVBQXVDLEVBQXZDLEVBQTJDLElBQTNDLENBdEJBLENBQUE7QUFBQSxFQXdCQSxPQUFBLEdBQVUsSUFBSSxDQUFDLEtBQUssQ0FBQyxRQUFRLENBQUMsZ0JBQXBCLENBQUEsQ0F4QlYsQ0FBQTtBQUFBLEVBeUJBLEtBQUEsR0FBUSxJQUFJLENBQUMsR0FBRyxDQUFDLEtBQVQsQ0FBQSxDQXpCUixDQUFBO0FBMkJBLE9BQVMsOEJBQVQsR0FBQTtBQUNFLElBQUEsSUFBQSxHQUFPLEtBQUssQ0FBQyxNQUFOLENBQWEsQ0FBQSxHQUFJLEVBQWpCLEVBQXFCLENBQXJCLEVBQXdCLE1BQXhCLENBQVAsQ0FBQTtBQUFBLElBQ0EsSUFBSSxDQUFDLElBQUksQ0FBQyxPQUFPLENBQUMsQ0FBbEIsR0FBc0IsQ0FEdEIsQ0FBQTtBQUFBLElBRUEsSUFBSSxDQUFDLElBQUksQ0FBQyxNQUFNLENBQUMsQ0FBakIsR0FBcUIsR0FBQSxHQUFNLElBQUksQ0FBQyxNQUFMLENBQUEsQ0FBQSxHQUFnQixHQUYzQyxDQURGO0FBQUEsR0EzQkE7U0FnQ0EsU0FBQSxHQUFZLElBQUksQ0FBQyxHQUFHLENBQUMsSUFBVCxDQUFjLEVBQWQsRUFBa0IsRUFBbEIsRUFBc0IsVUFBdEIsRUFBa0M7QUFBQSxJQUFBLElBQUEsRUFBTSxZQUFOO0FBQUEsSUFBb0IsSUFBQSxFQUFNLE1BQTFCO0dBQWxDLEVBakNMO0FBQUEsQ0FoQlQsQ0FBQTs7QUFBQSxXQW9EQSxHQUFjLFNBQUMsTUFBRCxFQUFTLElBQVQsR0FBQTtBQUNaLEVBQUEsSUFBSSxDQUFDLElBQUwsQ0FBQSxDQUFBLENBQUE7QUFBQSxFQUNBLEtBQUEsSUFBUyxFQURULENBQUE7U0FFQSxTQUFTLENBQUMsT0FBVixHQUFxQixTQUFBLEdBQVEsTUFIakI7QUFBQSxDQXBEZCxDQUFBOztBQUFBLE1BeURBLEdBQVMsU0FBQSxHQUFBO0FBQ1AsRUFBQSxJQUFJLENBQUMsT0FBTyxDQUFDLE9BQWIsQ0FBcUIsTUFBckIsRUFBNkIsU0FBN0IsQ0FBQSxDQUFBO0FBQUEsRUFDQSxJQUFJLENBQUMsT0FBTyxDQUFDLE9BQWIsQ0FBcUIsS0FBckIsRUFBNEIsU0FBNUIsQ0FEQSxDQUFBO0FBQUEsRUFFQSxJQUFJLENBQUMsT0FBTyxDQUFDLE9BQWIsQ0FBcUIsTUFBckIsRUFBNkIsS0FBN0IsRUFBb0MsV0FBcEMsRUFBaUQsSUFBakQsRUFBdUQsSUFBdkQsQ0FGQSxDQUFBO0FBQUEsRUFJQSxNQUFNLENBQUMsSUFBSSxDQUFDLFFBQVEsQ0FBQyxDQUFyQixHQUF5QixDQUp6QixDQUFBO0FBTUEsRUFBQSxJQUFHLE9BQU8sQ0FBQyxJQUFJLENBQUMsTUFBaEI7QUFDRSxJQUFBLE1BQU0sQ0FBQyxJQUFJLENBQUMsUUFBUSxDQUFDLENBQXJCLEdBQXlCLENBQUEsR0FBekIsQ0FBQTtBQUFBLElBQ0EsTUFBTSxDQUFDLFVBQVUsQ0FBQyxJQUFsQixDQUF1QixNQUF2QixDQURBLENBREY7R0FBQSxNQUdLLElBQUcsT0FBTyxDQUFDLEtBQUssQ0FBQyxNQUFqQjtBQUNILElBQUEsTUFBTSxDQUFDLElBQUksQ0FBQyxRQUFRLENBQUMsQ0FBckIsR0FBeUIsR0FBekIsQ0FBQTtBQUFBLElBQ0EsTUFBTSxDQUFDLFVBQVUsQ0FBQyxJQUFsQixDQUF1QixPQUF2QixDQURBLENBREc7R0FBQSxNQUFBO0FBSUgsSUFBQSxNQUFNLENBQUMsVUFBVSxDQUFDLElBQWxCLENBQUEsQ0FBQSxDQUFBO0FBQUEsSUFDQSxNQUFNLENBQUMsS0FBUCxHQUFlLENBRGYsQ0FKRztHQVRMO0FBZ0JBLEVBQUEsSUFBRyxPQUFPLENBQUMsRUFBRSxDQUFDLE1BQVgsSUFBcUIsTUFBTSxDQUFDLElBQUksQ0FBQyxRQUFRLENBQUMsSUFBN0M7V0FDRSxNQUFNLENBQUMsSUFBSSxDQUFDLFFBQVEsQ0FBQyxDQUFyQixHQUF5QixDQUFBLElBRDNCO0dBakJPO0FBQUEsQ0F6RFQsQ0FBQTs7QUFBQSxJQThFQSxHQUFXLElBQUEsTUFBTSxDQUFDLElBQVAsQ0FBWSxHQUFaLEVBQWlCLEdBQWpCLEVBQXNCLE1BQU0sQ0FBQyxJQUE3QixFQUFtQyxFQUFuQyxFQUF1QztBQUFBLEVBQUEsT0FBQSxFQUFTLE9BQVQ7QUFBQSxFQUFrQixNQUFBLEVBQVEsTUFBMUI7QUFBQSxFQUFrQyxNQUFBLEVBQVEsTUFBMUM7Q0FBdkMsQ0E5RVgsQ0FBQSIsImZpbGUiOiJnZW5lcmF0ZWQuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlc0NvbnRlbnQiOlsiKGZ1bmN0aW9uIGUodCxuLHIpe2Z1bmN0aW9uIHMobyx1KXtpZighbltvXSl7aWYoIXRbb10pe3ZhciBhPXR5cGVvZiByZXF1aXJlPT1cImZ1bmN0aW9uXCImJnJlcXVpcmU7aWYoIXUmJmEpcmV0dXJuIGEobywhMCk7aWYoaSlyZXR1cm4gaShvLCEwKTt0aHJvdyBuZXcgRXJyb3IoXCJDYW5ub3QgZmluZCBtb2R1bGUgJ1wiK28rXCInXCIpfXZhciBmPW5bb109e2V4cG9ydHM6e319O3Rbb11bMF0uY2FsbChmLmV4cG9ydHMsZnVuY3Rpb24oZSl7dmFyIG49dFtvXVsxXVtlXTtyZXR1cm4gcyhuP246ZSl9LGYsZi5leHBvcnRzLGUsdCxuLHIpfXJldHVybiBuW29dLmV4cG9ydHN9dmFyIGk9dHlwZW9mIHJlcXVpcmU9PVwiZnVuY3Rpb25cIiYmcmVxdWlyZTtmb3IodmFyIG89MDtvPHIubGVuZ3RoO28rKylzKHJbb10pO3JldHVybiBzfSkiLCJjb25zb2xlLmxvZyAnaGVsbG8gZmFnJ1xuXG5wcmVsb2FkID0gLT5cbiAgY29uc29sZS5sb2cgJzpwcmVsb2FkJ1xuICBnYW1lLmxvYWQuaW1hZ2UgJ3NreScsICcvYXNzZXRzL2ltYWdlcy9za3kucG5nJ1xuICBnYW1lLmxvYWQuaW1hZ2UgJ2dyb3VuZCcsICcvYXNzZXRzL2ltYWdlcy9wbGF0Zm9ybS5wbmcnXG4gIGdhbWUubG9hZC5pbWFnZSAnc3RhcicsICcvYXNzZXRzL2ltYWdlcy9zdGFyLnBuZydcbiAgZ2FtZS5sb2FkLnNwcml0ZXNoZWV0ICdkdWRlJywgJy9hc3NldHMvaW1hZ2VzL2R1ZGUucG5nJywgMzIsIDQ4XG5cbnBsYXRmb3JtcyA9IG51bGxcbnBsYXllciA9IG51bGxcbmN1cnNvcnMgPSBudWxsXG5zdGFycyA9IG51bGxcbnNjb3JlVGV4dCA9IG51bGxcbnNjb3JlID0gMFxuXG5jcmVhdGUgPSAtPlxuICBjb25zb2xlLmxvZyAnOmNyZWF0ZSdcbiAgZ2FtZS5hZGQuc3ByaXRlIDAsIDAsICdza3knXG4gIHBsYXRmb3JtcyA9IGdhbWUuYWRkLmdyb3VwKClcbiAgZ3JvdW5kID0gcGxhdGZvcm1zLmNyZWF0ZSAwLCBnYW1lLndvcmxkLmhlaWdodCAtIDY0LCAnZ3JvdW5kJ1xuICBncm91bmQuc2NhbGUuc2V0VG8gMiwgMlxuICBncm91bmQuYm9keS5pbW1vdmFibGUgPSB0cnVlXG5cbiAgbGVkZ2UgPSBwbGF0Zm9ybXMuY3JlYXRlIDQwMCwgNDAwLCAnZ3JvdW5kJ1xuICBsZWRnZS5ib2R5LmltbW92YWJsZSA9IHRydWVcblxuICBsZWRnZSA9IHBsYXRmb3Jtcy5jcmVhdGUgLTE1MCwgMjUwLCAnZ3JvdW5kJ1xuICBsZWRnZS5ib2R5LmltbW92YWJsZSA9IHRydWVcblxuICBnYW1lLmFkZC5zcHJpdGUgMCwgMCwgJ3N0YXInXG5cbiAgcGxheWVyID0gZ2FtZS5hZGQuc3ByaXRlIDMyLCBnYW1lLndvcmxkLmhlaWdodCAtIDE1MCwgJ2R1ZGUnXG5cbiAgcGxheWVyLmJvZHkuYm91bmNlLnkgPSAwLjJcbiAgcGxheWVyLmJvZHkuZ3Jhdml0eS55ID0gNlxuICBwbGF5ZXIuYm9keS5jb2xsaWRlV29ybGRCb3VuZHMgPSB0cnVlXG5cbiAgcGxheWVyLmFuaW1hdGlvbnMuYWRkICdsZWZ0JywgWzAuLjNdLCAxMCwgdHJ1ZVxuICBwbGF5ZXIuYW5pbWF0aW9ucy5hZGQgJ3JpZ2h0JywgWzUuLjhdLCAxMCwgdHJ1ZVxuXG4gIGN1cnNvcnMgPSBnYW1lLmlucHV0LmtleWJvYXJkLmNyZWF0ZUN1cnNvcktleXMoKVxuICBzdGFycyA9IGdhbWUuYWRkLmdyb3VwKClcbiBcbiAgZm9yIGkgaW4gWzAuLjEyXVxuICAgIHN0YXIgPSBzdGFycy5jcmVhdGUgaSAqIDcwLCAwLCAnc3RhcidcbiAgICBzdGFyLmJvZHkuZ3Jhdml0eS55ID0gNlxuICAgIHN0YXIuYm9keS5ib3VuY2UueSA9IDAuNyArIE1hdGgucmFuZG9tKCkgKiAwLjJcblxuICBzY29yZVRleHQgPSBnYW1lLmFkZC50ZXh0IDE2LCAxNiwgJ3Njb3JlOiAwJywgZm9udDogJzMycHggYXJpYWwnLCBmaWxsOiAnIzAwMCdcblxuXG5jb2xsZWN0U3RhciA9IChwbGF5ZXIsIHN0YXIpLT5cbiAgc3Rhci5raWxsKClcbiAgc2NvcmUgKz0gMTBcbiAgc2NvcmVUZXh0LmNvbnRlbnQgPSBcIlNjb3JlOiAje3Njb3JlfVwiXG5cbnVwZGF0ZSA9IC0+XG4gIGdhbWUucGh5c2ljcy5jb2xsaWRlIHBsYXllciwgcGxhdGZvcm1zXG4gIGdhbWUucGh5c2ljcy5jb2xsaWRlIHN0YXJzLCBwbGF0Zm9ybXNcbiAgZ2FtZS5waHlzaWNzLm92ZXJsYXAgcGxheWVyLCBzdGFycywgY29sbGVjdFN0YXIsIG51bGwsIHRoaXNcblxuICBwbGF5ZXIuYm9keS52ZWxvY2l0eS54ID0gMFxuXG4gIGlmIGN1cnNvcnMubGVmdC5pc0Rvd25cbiAgICBwbGF5ZXIuYm9keS52ZWxvY2l0eS54ID0gLTE1MFxuICAgIHBsYXllci5hbmltYXRpb25zLnBsYXkgJ2xlZnQnXG4gIGVsc2UgaWYgY3Vyc29ycy5yaWdodC5pc0Rvd25cbiAgICBwbGF5ZXIuYm9keS52ZWxvY2l0eS54ID0gMTUwXG4gICAgcGxheWVyLmFuaW1hdGlvbnMucGxheSAncmlnaHQnXG4gIGVsc2VcbiAgICBwbGF5ZXIuYW5pbWF0aW9ucy5zdG9wKClcbiAgICBwbGF5ZXIuZnJhbWUgPSA0XG5cbiAgaWYgY3Vyc29ycy51cC5pc0Rvd24gJiYgcGxheWVyLmJvZHkudG91Y2hpbmcuZG93blxuICAgIHBsYXllci5ib2R5LnZlbG9jaXR5LnkgPSAtMzUwXG5cblxuZ2FtZSA9IG5ldyBQaGFzZXIuR2FtZSA4MDAsIDYwMCwgUGhhc2VyLkFVVE8sICcnLCBwcmVsb2FkOiBwcmVsb2FkLCBjcmVhdGU6IGNyZWF0ZSwgdXBkYXRlOiB1cGRhdGVcbiJdfQ==
