import { useRef } from 'react';

function NewFeed() {
  return (
    <div>
      <form>
        <div>
          <textarea rows="4" cols="50">
            Broadcast your thoughts here!
          </textarea>
        </div>
        <div>
          <input type="submit" value="Add" />
        </div>
      </form>
    </div>
  );
}
export default NewFeed;
