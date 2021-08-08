import { useRef } from "react";

function NewFeedForm(props) {
  const feedInputRef = useRef();
  function submitHandler(event) {
    event.preventDefault();
    const feedContent= feedInputRef.current.value;
    const currentTime = new Date(); 
    const feedData ={
      postedBy: 'Nattada',
      postedOn: currentTime,
      content:feedContent
    };

    props.onAddFeed(feedData);
  }

  return (
    <div>
      <form onSubmit={submitHandler}>
        <div>
          <label htmlFor="description"></label>
          <textarea
            id="feedsContent"
            required
            rows="4"
            maxlength="200"
            ref={feedInputRef}
          >
            Broadcast your thoughts here!
          </textarea>
        </div>
        <div>
          <button>Send</button>
        </div>
      </form>
    </div>
  );
}
export default NewFeedForm;
