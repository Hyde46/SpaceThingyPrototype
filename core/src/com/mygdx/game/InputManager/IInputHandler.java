package com.mygdx.game.InputManager;

public interface IInputHandler
{
    void OnTouch(TouchData td);
    void OnRelease(TouchData td);
    void OnDrag(TouchData td);
    void OnHold(TouchData td);
    void OnSwipe(TouchData td);
}
