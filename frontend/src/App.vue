<script setup></script>

<template>
  <router-view>
    <div id="welcomeText">
      <h1>payment DEMO</h1>
      <h2>for e-commerce websites</h2>
    </div>
    <!--ALERT 1-->
    <div class="alert alert-primary" role="alert" style="margin-top: 80px">
      website made by Jebali Mezen.<br />Contact me
      <a href="https://portfolio-n5h8.onrender.com" target="_blank">here</a
      >.<br />
      2024 &copy; ALL OF THE RIGHTS RESERVED
    </div>
    <!--ALERT 2-->
    <div
      class="alert alert-primary alert-dismissible fade show"
      role="alert"
      style="text-align: left"
    >
      this is a simple demo of a payment gateway demonstration using "FLOUCI"
      API.<br />
      all the prices & payment procedures are fake and made for development and
      testing, for more information about how you could fill the payment forum
      you may have a look at "FLOUCI"
      <a
        href="https://flouci.stoplight.io/docs/flouci-payment-apis/6c9b5ad7358c9-fr-flouci-payment-api#environnement-de-test"
        target="_blank"
        >API documentation</a
      >.
      <button
        type="button"
        class="close"
        data-dismiss="alert"
        aria-label="Close"
      >
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <!--ITEMS-->
    <div id="items">
      <div
        class="product-container"
        v-for="(product, index) in products"
        :key="index"
      >
        <img
          class="product-image"
          :src="
            product.image.includes('http')
              ? product.image
              : getImageURL(product.image)
          "
          :alt="product.title"
        />
        <div class="product-details">
          <h2 class="product-title">{{ product.title }}</h2>
          <p class="product-description">
            {{ product.description }}
          </p>
          <div class="product-actions">
            <span class="product-price"
              >{{ formatNumber(product.price / 1000) }} TND</span
            >
            <button
              class="buy-button"
              @click="AddProduct(index, product.price)"
            >
              Buy Now
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--CART-->
    <!--CART LOGO-->
    <div
      style="position: absolute; right: 40px; top: 50px; cursor: pointer"
      @click="switchDisplayCart"
    >
      <div
        style="
          background: rgb(74, 133, 223);
          padding: 3px;
          border-radius: 100%;
          color: white;
          font-weight: bolder;
        "
      >
        {{ cart.length }}
      </div>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="32"
        height="32"
        fill="currentColor"
        class="bi bi-cart4"
        viewBox="0 0 16 16"
      >
        <path
          d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l.5 2H5V5zM6 5v2h2V5zm3 0v2h2V5zm3 0v2h1.36l.5-2zm1.11 3H12v2h.61zM11 8H9v2h2zM8 8H6v2h2zM5 8H3.89l.5 2H5zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"
        />
      </svg>
    </div>
    <div
      id="cart"
      style="transform: scaleY(0); transition: 0.5s; transform-origin: top"
    >
      <button
        style="
          padding: 3px;
          background-color: gainsboro;
          border-bottom: solid black 2px;
          border-radius: 4px;
          position: absolute;
          right: 0;
        "
        @click="switchDisplayCart"
      >
        close
      </button>
      <div v-if="!cart.length" style="padding: 4px; color: rgb(167, 167, 167)">
        There's nothing added to cart yet !
      </div>
      <div class="added-items">
        <div
          class="product-cart-container"
          v-for="(product, index) in cart"
          :key="index"
        >
          <img
            class="product-cart-image"
            :src="
              product.image.includes('http')
                ? product.image
                : getImageURL(product.image)
            "
            :alt="product.title"
          />
          <div class="product-cart-details">
            <h2 class="product-cart-title">{{ product.title }}</h2>

            <div class="product-cart-actions">
              <span class="product-cart-price"
                >{{ formatNumber(product.price / 1000) }} TND</span
              >
              <div style="cursor: pointer" @click="removeProduct(index)">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="18"
                  height="18"
                  fill="currentColor"
                  class="bi bi-dash-square"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"
                  />
                  <path
                    d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8"
                  />
                </svg>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr />
      <div class="cart-action">
        <span id="totalPrice">
          Total Price :
          {{ formatNumber(totalPrice / 1000) }} TND </span
        ><br />
        <span
          style="color: red; text-align: center"
          v-if="totalPrice > 2000000"
        >
          *Flouci* payment gateway transfer only 2,000TND as maximum!
        </span>
        <div class="checkbox-wrapper-29" style="margin: 3%; margin-left: 40%">
          <label class="checkbox" style="color: rgba(3, 102, 214, 0.8)">
            <input type="checkbox" class="checkbox__input" id="paymentType" />
            <span class="checkbox__label"></span>
            Payment With QR code
          </label>
        </div>
        <button
          class="buy-button"
          id="cart-button"
          @click="buyProducts(totalPrice)"
          disabled
        >
          CHECKOUT
        </button>
      </div>
    </div>
  </router-view>
</template>

<script>
import axios from "axios";
import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";
export default {
  name: "app",
  mounted() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("payment_id")) {
      const parameter = urlParams.get("payment_id");
      this.verifyProduct(parameter);
    }
  },
  data() {
    return {
      totalPrice: 0,
      cart: [],
      products: [
        {
          title: 'HISENSE 40A4KTUK 40" Smart Full HD LED TV',
          image:
            "https://media.currys.biz/i/currysprod/10250255_002?$l-large$&fmt=auto",
          description: `The world is colourful, so why not see more of it on this Hisense A4KT TV? Its Natural Colour Enhancer technology does what it says on the tin –
          whether you're watching sports or movies, you'll see colours way they were meant to be seen.`,
          price: 1459000,
        },
        {
          title: "Xiaomi Redmi Note 10 Pro 128GB 6GB RAM",
          image: "https://i.ebayimg.com/images/g/WJQAAOSw-zxiRI~w/s-l1200.webp",
          description: `A perfect balance between aesthetics and efficiency, the Xiaomi Redmi Note 10 Pro knows how to fulfill any of your desires. For this,
          it has a superb AMOLED 120 Hz touch screen of 6.67" displaying a Full HD+ resolution of 1080 x 2400 pixels, with, integrates a Qualcomm Snapdragon
          732G Octo-Core processor clocked at 2.3 GHz, with, a storage space of 128 GB and 6 GB of RAM to ensure fluidity and speed during all your uses.`,
          price: 999000,
        },
        {
          title: "3-pack DryMove™ Sports T-shirts",
          image:
            "https://tn.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/22/5628/1.jpg?9710",
          description: `Because as one of the essential pieces of the men's dressing room, the designers compete creatively to offer men's t-shirts that are increasingly
          adapted to their daily lives. Basic, refined or deliciously offbeat, it's gone for a parade full of modernity where the comfort of the models is matched only by their look.`,
          price: 89560,
        },
        {
          title: "lirene Face Screen Adult Spf50 +/- 50 ml",
          image:
            "https://tn.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/56/3738/1.jpg?4245",
          description: `The protection of the skin with the’use of SUNscreens most often focuses on the prevention of sunburn caused by ultraviolet rays (UVB and UVA).
          Recent studies indicate the negative effects of infrared radiation on the skin.`,
          price: 25990,
        },
        {
          title: "TOTAL High performance automatic stripping pliers",
          image:
            "https://tn.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/80/3538/1.jpg?5308",
          description: `Offering an exceptional blend of modernity, design, ease of use, wide range and competitive prices, TOTAL TOOLS stands out.
          To such an extent that TOTAL is one of the fastest growing tool brands in tunisia`,
          price: 45500,
        },
        {
          title: "Karcher SE4 INJECTOR/EXTRACTOR DEVICE",
          image:
            "https://tn.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/03/3838/1.jpg?4008",
          description: `The SE 4 injector-extractor cleans textile surfaces in comfort and particularly in depth for
           hygiene up to the heart of the fibers. Ideal for families, allergy sufferers and homes with animals.Meeting between cleaning to the heart of the fibers, ergonomic comfort and multifunctionality:
           the SE 4 injector-extractor with an attractive sled design guarantees hygienic cleanliness`,
          price: 1290999,
        },
        {
          title:
            "Biolux 3-in-1 multifunction kneading robot - Stainless steel - 5L - 1300W",
          image:
            "https://tn.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/73/6386/1.jpg?4487",
          description: `Robot pastry chef with elegant and contemporary design.
                        Featuring’an exceptional power of 1300 W. Its stainless steel bowl of 5 L and its pastry kit (beater, mixer, whip, kneader)
                        make him an indispensable companion in the kitchen !`,
          price: 396599,
        },
      ],
    };
  },
  methods: {
    /* INTERACTION WITH BACKEND */
    buyProducts(amount) {
      const cardPayment = document.getElementById("paymentType").checked
        ? false
        : true;
      const requestData = { cardsPayment: cardPayment, amount: amount };
      axios
        .post("http://localhost:9090/generatePayment", requestData)
        .then((res) => {
          window.location.href = res.data.result.link;
        })
        .catch((e) => {
          toast.error(e.message, {
            autoClose: 2000,
            position: toast.POSITION.TOP_LEFT,
          });
        });
    },
    verifyProduct(productID) {
      toast.warn("Verifying Payment process...", {
        autoClose: 3000,
        position: toast.POSITION.TOP_LEFT,
      });
      axios
        .get("http://localhost:9090/verifyPayment/" + productID)
        .then((res) => {
          toast.success("Payment successful", {
            autoClose: 3000,
            position: toast.POSITION.TOP_LEFT,
          });
        })
        .catch((e) => {
          toast.error(e.message, {
            autoClose: 2000,
            position: toast.POSITION.TOP_LEFT,
          });
        });
    },
    /* CART MANAGEMENT */
    AddProduct(index, price) {
      this.totalPrice += price;
      this.cart.push(this.products[index]);
      toast.info("Product added to cart!", {
        autoClose: 2000,
        position: toast.POSITION.TOP_LEFT,
      });
    },
    removeProduct(index) {
      this.totalPrice -= this.cart[index].price;
      this.cart.splice(index, 1);
    },
    /* OTHER FUNCTIONS */
    formatNumber(number) {
      const formatted = new Intl.NumberFormat().format(number);
      return formatted;
    },
    getImageURL(imageName) {
      const imageUrl = new URL(`../assets/${imageName}`, import.meta.url).href;
      return imageUrl;
    },
    switchDisplayCart() {
      document.getElementById("cart").style.transform == "scaleY(0)"
        ? (document.getElementById("cart").style.transform = "scaleY(1)")
        : (document.getElementById("cart").style.transform = "scaleY(0)");
    },
  },
  watch: {
    totalPrice: {
      handler(newVal, oldVal) {
        document.getElementById("cart-button").disabled = false;
        document.getElementById("totalPrice").style.color = "black";
        if (newVal > 2000000) {
          document.getElementById("totalPrice").style.color = "red";
          document.getElementById("cart-button").disabled = true;
        }
      },
    },
  },
};
</script>

<style>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}

#welcomeText {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
}

#items {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  margin: 2rem;
  margin-top: 100px;
  width: 100%;
}

h1,
h2 {
  font-family: "KG HAPPY";
  font-size: x-large;
  color: rgb(62, 135, 231);
}

h1 {
  color: #744848;
  border-radius: 6px;
  font-size: xx-large;
}

.product-container {
  max-width: 24rem;
  margin: 0 auto;
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  overflow-x: hidden;
  cursor: pointer;
  transition: 0.6s;
}

.product-container:hover {
  transform: scale(1.05);
  box-shadow: 0 10px 14px rgba(0, 0, 0, 0.2);
}

.product-image {
  width: 100%;
  height: 12rem;
  object-fit: contain;
}

.product-details {
  padding: 1rem;
}

.product-title {
  font-size: 1.25rem;
  font-weight: 600;
  color: #2d3748;
}

.product-description {
  color: #718096;
  margin-top: 0.5rem;
}

.product-actions {
  display: flex;
  align-content: center;
  justify-content: space-between;
  margin-top: 1rem;
}

.product-price {
  font-size: 1.25rem;
  font-weight: 700;
}

.buy-button {
  padding: 0.5rem 1rem;
  background-color: #3b82f6;
  color: white;
  font-size: 0.875rem;
  font-weight: 600;
  border-radius: 0.25rem;
  cursor: pointer;
  outline: none;
  transition: 0.2s;
}

.buy-button:hover,
.buy-button:focus {
  background-color: #2563eb;
}

#cart {
  box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;
  position: absolute;
  right: 5px;
  top: 5px;
  width: 40%;
  background-color: white;
}

.added-items {
  margin-left: 2px;
}

.product-cart-container {
  margin-left: 5px;
}

.product-cart-image {
  width: 100px;
  height: 100px;
  padding: 0;
  margin: 0;
}

.product-cart-details {
  display: inline-block;
}

.product-cart-actions {
  width: 100%;
}

.product-cart-title {
  font-size: small;
  width: 60%;
  text-align: left;
  margin-left: 5px;
}

#totalPrice {
  font-weight: bold;
}

#cart-button {
  margin: 5px;
}
/* CUSTOM CHECKBOX DESIGN */
.checkbox-wrapper-29 {
  --size: 1rem;
  --background: #fff;
  font-size: var(--size);
}

.checkbox-wrapper-29 *,
.checkbox-wrapper-29 *::after,
.checkbox-wrapper-29 *::before {
  box-sizing: border-box;
}

.checkbox-wrapper-29 input[type="checkbox"] {
  visibility: hidden;
  display: none;
}

.checkbox-wrapper-29 .checkbox__label {
  width: var(--size);
}

.checkbox-wrapper-29 .checkbox__label:before {
  content: " ";
  display: block;
  height: var(--size);
  width: var(--size);
  position: absolute;
  top: calc(var(--size) * 0.125);
  left: 0;
  background: var(--background);
}

.checkbox-wrapper-29 .checkbox__label:after {
  content: " ";
  display: block;
  height: var(--size);
  width: var(--size);
  border: calc(var(--size) * 0.14) solid rgba(3, 102, 214, 0.8);
  transition: 200ms;
  position: absolute;
  top: calc(var(--size) * 0.125);
  left: 0;
  background: var(--background);
}

.checkbox-wrapper-29 .checkbox__label:after {
  transition: 100ms ease-in-out;
}

.checkbox-wrapper-29 .checkbox__input:checked ~ .checkbox__label:after {
  border-top-style: none;
  border-right-style: none;
  -ms-transform: rotate(-45deg); /* IE9 */
  transform: rotate(-45deg);
  height: calc(var(--size) * 0.5);
  border-color: green;
}

.checkbox-wrapper-29 .checkbox {
  position: relative;
  display: flex;
  cursor: pointer;
  /* Mobile Safari: */
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.checkbox-wrapper-29 .checkbox__label:after:hover,
.checkbox-wrapper-29 .checkbox__label:after:active {
  border-color: green;
}

.checkbox-wrapper-29 .checkbox__label {
  margin-right: calc(var(--size) * 0.45);
}
</style>
