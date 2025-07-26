export function useDragScroll(element: HTMLElement | null) {
  if (!element) return;

  let isDown = false;
  let startX = 0;
  let scrollLeft = 0;

  const onMouseDown = (e: MouseEvent) => {
    isDown = true;
    startX = e.pageX - element.offsetLeft;
    scrollLeft = element.scrollLeft;
    element.classList.add('cursor-grabbing');
  };

  const onMouseLeave = () => {
    isDown = false;
    element.classList.remove('cursor-grabbing');
  };

  const onMouseUp = () => {
    isDown = false;
    element.classList.remove('cursor-grabbing');
  };

  const onMouseMove = (e: MouseEvent) => {
    if (!isDown) return;
    e.preventDefault();
    const x = e.pageX - element.offsetLeft;
    const walk = (x - startX) * 1.5;
    element.scrollLeft = scrollLeft - walk;
  };

  element.addEventListener('mousedown', onMouseDown);
  element.addEventListener('mouseleave', onMouseLeave);
  element.addEventListener('mouseup', onMouseUp);
  element.addEventListener('mousemove', onMouseMove);

  return () => {
    element.removeEventListener('mousedown', onMouseDown);
    element.removeEventListener('mouseleave', onMouseLeave);
    element.removeEventListener('mouseup', onMouseUp);
    element.removeEventListener('mousemove', onMouseMove);
  };
}
