package com.example.data.remote

import com.example.data.model.ReelIdea
import com.example.data.model.ReelIdeaResponse
import com.example.data.model.ReelScript
import java.util.UUID

object PresetReelVault {

    fun getPresetForNiche(niche: String, language: String = "Hinglish"): ReelIdeaResponse {
        val lower = niche.lowercase()
        return when {
            lower.contains("fit") || lower.contains("gym") || lower.contains("health") || lower.contains("diet") ->
                getFitnessPreset(language)
            lower.contains("comed") || lower.contains("relat") || lower.contains("fun") || lower.contains("pov") ->
                getComedyPreset(language)
            lower.contains("food") || lower.contains("cook") || lower.contains("recipe") || lower.contains("eat") ->
                getFoodPreset(language)
            lower.contains("financ") || lower.contains("money") || lower.contains("invest") || lower.contains("crypto") || lower.contains("stock") ->
                getFinancePreset(language)
            lower.contains("tech") || lower.contains("ai") || lower.contains("code") || lower.contains("app") || lower.contains("phone") ->
                getTechPreset(language)
            lower.contains("fashion") || lower.contains("style") || lower.contains("outfit") || lower.contains("look") ->
                getFashionPreset(language)
            lower.contains("productiv") || lower.contains("study") || lower.contains("focus") || lower.contains("habit") ->
                getProductivityPreset(language)
            else ->
                getGenericCreatorPreset(niche, language)
        }
    }

    private fun getFitnessPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Fitness & Gym",
            dateContext = "Trending New Year/Q4 Fitness consistency wave & high-protein hacks",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "The 1 Protein Mistake Costing You Muscle",
                    hookLine = if (isHinglish) "Stop eating paneer like this! Isse tumhara protein waste ho raha hai." else "Stop eating your protein like this! You're losing 40% absorption.",
                    trendingElement = "Bass drop transition on 3rd second (Audio: phonk / upbeat motivational beat)",
                    script = ReelScript(
                        sec0_3 = "Visual: Holding a raw block of paneer / protein scoop directly into camera lens. Bold Red Text: 'STOP DOING THIS'. Line: 'Stop eating paneer like this! Isse tumhara muscle protein waste ho raha hai.'",
                        sec3_15 = "Visual: Quick cut showing gut digestion graphics or cooking paneer over high flame. Line: '90% log paneer ko deep fry karte hain ya meal ke saath carbs overload kar dete hain, jisse absorption slow aur digestion heavy ho jata hai.'",
                        sec15_25 = "Visual: Cutting fresh raw paneer into clean cubes with lemon + black pepper sprinkle. Line: 'Simple fix: Paneer ko lightly saute karo ya raw khao, lemon aur black salt ke saath for max bioavailability. 100g = clean 18g protein.'",
                        sec25_30 = "Visual: Pointing to screen with thumbs up. Line: 'Comment 'PROTEIN' aur main bhejunga 5 high-protein vegetarian recipes directly in your DM! Save karo.'",
                    ),
                    caption = "Agar paneer kha ke bhi muscle build nahi ho rahi, to ye absorption mistake fix karo! 🏋️ Save this for your grocery day.",
                    hashtags = listOf("#FitnessHacks", "#GymDiet", "#ProteinSource", "#MuscleBuilding", "#FitIndia"),
                    whyItWorks = "Pattern-interrupt hook challenges a common Indian fitness belief, triggering instant stop-scroll and saves.",
                    formatType = "Trending Audio & Myth Buster",
                    difficulty = "Easy Shoot Today (No props needed)",
                    hookType = "Pattern Interrupt"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "POV: When Your Trainer Says 'Bas Last Rep'",
                    hookLine = if (isHinglish) "Trainer bola: 'Ek aur karle, meri kasam!' Aur tabhi..." else "Trainer said: 'Just one more rep, on me!' And then...",
                    trendingElement = "Trending comedic suspense track (e.g. 'Oh No' remix or dramatic orchestral swell)",
                    script = ReelScript(
                        sec0_3 = "Visual: Extreme close-up of shaking sweaty face under a barbell/dumbbell. Line: 'Trainer bola: Ek aur karle, meri kasam! Aur tabhi meri aatma sharir se bahar aa gayi.'",
                        sec3_15 = "Visual: B-roll slow-mo struggle, legs vibrating like a washing machine. Face turning completely red trying to push 1 more rep.",
                        sec15_25 = "Visual: Cut to trainer standing casually checking Instagram on phone saying 'Abhi to warm-up chal raha tha bro!'",
                        sec25_30 = "Visual: You collapsing onto the floor mat looking up. Line: 'Tag your trainer ya gym partner jo tumhari jaan lene pe tula rehta hai!'",
                    ),
                    caption = "Gym bro betrayal at its finest 😂 Tag that trainer whose 'last rep' never ends!",
                    hashtags = listOf("#GymComedy", "#GymRelatable", "#WorkoutMeme", "#FitnessHumor", "#GymBro"),
                    whyItWorks = "Universal gym humor creates high tag-to-share ratio, exploding organic reach on Instagram Reels algorithm.",
                    formatType = "Storytelling & Relatable POV",
                    difficulty = "Medium Production (Gym partner required)",
                    hookType = "Relatable Callout"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "3 Fat Loss Hacks Nobody Tells You",
                    hookLine = if (isHinglish) "90% log ye 1 galti karte hain aur unka belly fat kabhi kam nahi hota." else "90% of people make this 1 mistake and their belly fat never goes away.",
                    trendingElement = "Snappy snap-sound transitions with clean minimal background music",
                    script = ReelScript(
                        sec0_3 = "Visual: Finger point to camera with text banner overlay: '90% FAIL HERE'. Line: '90% log ye 1 galti karte hain aur unka belly fat kabhi kam nahi hota.'",
                        sec3_15 = "Visual: Holding a glass of water and stepping on a treadmill. Line: 'Number 1: Tum cardio overdo karte ho par daily 8,000 steps nahi lete. Number 2: Liquid calories like chai and juices count nahi karte.'",
                        sec15_25 = "Visual: High-protein plate showing dal, eggs/tofu and salad. Line: 'Number 3: Har meal mein 25g protein skip karte ho jisse cravings badhti hain. Fix these 3 and see fat drop in 3 weeks.'",
                        sec25_30 = "Visual: Holding up phone screen. Line: 'Follow karo for realistic, science-backed fitness tips jo actually follow ho sakein.'",
                    ),
                    caption = "Cardio karne se pehle ye 3 realistic fat-loss rules implement karo. No crash diets, pure consistency! 📉",
                    hashtags = listOf("#FatLossTips", "#WeightLossJourney", "#CalorieDeficit", "#FitnessMotivation", "#HealthTips"),
                    whyItWorks = "Curiosity-gap hook with quick listicle format guarantees high watch-time (AWT) completion metrics.",
                    formatType = "Educational / Quick Listicle",
                    difficulty = "Easy Shoot Today (Just phone & natural light)",
                    hookType = "Curiosity Gap"
                )
            )
        )
    }

    private fun getComedyPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Comedy & Relatable POV",
            dateContext = "Trending office & family relatable humor trends",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "POV: Salary Day vs 5th of Month",
                    hookLine = if (isHinglish) "30 tarikh: 'Bhai Starbucks chalte hain!' 5 tarikh: 'Bhai Maggie ke packets kitne bache hain?'" else "Salary day: 'Ordering gourmet!' 5 days later: 'Is tap water considered soup?'",
                    trendingElement = "Trending transition audio: upbeat luxury beat cutting abruptly to crying flute/tabla fail sound",
                    script = ReelScript(
                        sec0_3 = "Visual: Wearing blazer/sunglasses, holding wallet like a king. Line: '30 tarikh: Bhai aaj bill main pay karunga!'",
                        sec3_15 = "Visual: Fast whip transition to sitting on floor in tattered t-shirt counting coins. Fast montage of credit card notification popups.",
                        sec15_25 = "Visual: Opening fridge to find literally half a lemon and water bottle. Dramatic emotional stare at the lemon.",
                        sec25_30 = "Visual: Looking into camera with folded hands. Line: 'Apne us dost ko bhejo jiska har mahine yehi haal hota hai 😂'",
                    ),
                    caption = "Salary aati hai ya bus station pe pause leke chali jaati hai? 💸 Send this to your broke bestie!",
                    hashtags = listOf("#SalaryDay", "#MiddleClassMemes", "#RelatableReels", "#DesiHumor", "#FunnyReels"),
                    whyItWorks = "Universal financial struggle humor creates massive private WhatsApp & DM shares, algorithm's top metric.",
                    formatType = "Trending Audio & Fast Transition",
                    difficulty = "Easy Shoot Today (Single room & 2 outfits)",
                    hookType = "Relatable Callout"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "When Mummy Catches You Ordering Food",
                    hookLine = if (isHinglish) "Swiggy wale ne doorbell bajayi aur Mummy ne jo bola sunke dharte hil gayi!" else "The doorbell rang from delivery and what Mom yelled shook the entire neighborhood!",
                    trendingElement = "Dramatic suspense thriller BGM (CID or Crime Patrol sting)",
                    script = ReelScript(
                        sec0_3 = "Visual: Eyes wide open looking at main door in extreme slow motion. Line: 'Doorbell bajte hi Mummy ne bola: Kaun hai ye roz ka khana mangwane wala?!'",
                        sec3_15 = "Visual: You rushing down hallway like secret agent trying to intercept delivery guy before Mom opens the door.",
                        sec15_25 = "Visual: Mom already at the door holding a belan saying 'Ghar ki tinda-lauki mein zahar mila tha kya beta?'",
                        sec25_30 = "Visual: You sheepishly eating cold pizza inside bathroom. Line: 'Share with that friend jo ghar pe chup chup ke order karta hai!'",
                    ),
                    caption = "Ghar ki sabzi vs Zomato parcel: The eternal Indian battle 🍕 Mom 1 - 0 Swiggy.",
                    hashtags = listOf("#DesiMoms", "#IndianComedy", "#SwiggyMoments", "#FunnyVideos", "#RelatablePOV"),
                    whyItWorks = "Indian household storytelling is evergreen viral gold because 100% of the audience relates instantly.",
                    formatType = "Storytelling & Character Sketch",
                    difficulty = "Medium Production (Two characters / POV switch)",
                    hookType = "Curiosity Gap"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "Controversial Opinion: 'Chai Lovers' Are Actually...",
                    hookLine = if (isHinglish) "Ye ek cheez kehne ke baad shayad aadha India mujhe block kar dega." else "Saying this might get me cancelled by half the country, but hear me out.",
                    trendingElement = "Subtle tension vinyl scratch audio with clean talking-head setup",
                    script = ReelScript(
                        sec0_3 = "Visual: Sipping tea with straight serious deadpan face. Line: 'Ye kehne ke baad shayad aadha India mujhe dosti se nikal de, but tum chai lover nahi ho.'",
                        sec3_15 = "Visual: Showing cup with 4 spoons of sugar. Line: 'Tumhe chai pasand nahi, tumhe 4 chammach cheeni aur meetha doodh peene ki aadat hai. Authentic black tea do to tumhara muh ban jata hai!'",
                        sec15_25 = "Visual: Dramatic shrug to camera. Line: 'Accept it, you are a sugar addict disguised as a philosopher at the local tapri.'",
                        sec25_30 = "Visual: Smirk at camera. Line: 'Comment mein gaaliyan dene se pehle batao: With sugar or without sugar? Let the war begin!'",
                    ),
                    caption = "Tapri philosophers don't come at me in the comments ☕ Sugar rush or real tea connoisseur?",
                    hashtags = listOf("#ChaiLovers", "#ChaiTime", "#UnpopularOpinion", "#DesiReels", "#HotTakes"),
                    whyItWorks = "Controversial opinion hooks ignite comment section wars, driving algorithmic engagement through the roof.",
                    formatType = "Hot Take / Rant",
                    difficulty = "Easy Shoot Today (Just sitting on a chair with a mug)",
                    hookType = "Controversial Opinion"
                )
            )
        )
    }

    private fun getFoodPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Food & Street Recipes",
            dateContext = "Quick 10-minute gourmet street food recreations",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "The 10-Minute Chilli Garlic Maggi Hack",
                    hookLine = if (isHinglish) "Maggi banane ka ye tareeka dekh liya to normal Maggi dobara nahi banaoge." else "If you make instant noodles like this, you will never go back to boiling it plain.",
                    trendingElement = "Sizzling ASMR sound (hot oil poured over minced garlic and chilli flakes)",
                    script = ReelScript(
                        sec0_3 = "Visual: Extreme close-up sizzle of smoking hot sesame oil hitting crushed garlic, spring onions, and peri-peri masala. Line: 'Maggi banane ka ye tareeka dekh liya to normal Maggi dobara kabhi nahi banaoge!'",
                        sec3_15 = "Visual: Fast cuts showing boiling noodles, adding dark soy sauce, chilli crisps, and tastemaker into a deep bowl.",
                        sec15_25 = "Visual: Mixing noodles in slow motion, glossy oily red sauce coating every strand, topped with toasted sesame seeds and melted cheese pull.",
                        sec25_30 = "Visual: Taking the first steaming bite with eyes closed in culinary bliss. Line: 'Comment 'RECIPE' aur ingredients ki exact measurement DM mein bhej dunga! Save karo.'",
                    ),
                    caption = "Midnight cravings ka ultimate 10-minute solution 🍜 Chilli Garlic Maggi hits different! Bookmark this.",
                    hashtags = listOf("#MaggiHack", "#StreetFoodRecipes", "#FoodieReels", "#QuickSnacks", "#MidnightCraving"),
                    whyItWorks = "High sensory ASMR visual + auditory hook triggers immediate dopamine cravings and recipe bookmarks.",
                    formatType = "Trending ASMR & Visual Sizzle",
                    difficulty = "Easy Shoot Today (Kitchen counter + Maggi pack)",
                    hookType = "Bold Claim"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "Street Vendor's Secret Masala Revealed",
                    hookLine = if (isHinglish) "Street food vendors ye 1 secret ingredient chhupate hain jo ghar pe nahi milta!" else "Street food stalls hide this 1 secret ingredient that restaurants never tell you about!",
                    trendingElement = "Fast rhythmic upbeat lo-fi hip hop beat with sharp cut transitions",
                    script = ReelScript(
                        sec0_3 = "Visual: Shaking a stainless steel masala dabba into a pan with fire flame flare. Line: 'Roadside thele wale ye ek secret ingredient kabhi nahi batate jisse taste double ho jata hai!'",
                        sec3_15 = "Visual: Revealing black salt, roasted cumin powder, dried mango powder, and a tiny pinch of citric acid (nimbu sat).",
                        sec15_25 = "Visual: Dusting this magic dust over hot crispy french fries / paneer tikka. Look at the vibrant orange glow.",
                        sec25_30 = "Visual: Plate presentation with mint chutney. Line: 'Agar street food lover ho to abhi follow button dabao daily secrets ke liye!'",
                    ),
                    caption = "The exact Chaat Masala blend that makes roadside street food 10x tastier 🔥 Try this at home tonight.",
                    hashtags = listOf("#SecretRecipe", "#DesiStreetFood", "#IndianSpices", "#KitchenHacks", "#RecipeReels"),
                    whyItWorks = "Secrets & insider reveals satisfy deep audience curiosity, yielding high watch-time and profile visits.",
                    formatType = "Educational / Secret Reveal",
                    difficulty = "Easy Shoot Today (Basic spices in kitchen)",
                    hookType = "Curiosity Gap"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "My Grandma's 50-Year-Old Chai Formula",
                    hookLine = if (isHinglish) "Dadi ne sikhayi thi ye chai trick: Adrak pehle nahi, is step pe daalna!" else "My grandmother taught me this tea rule: Never boil ginger with milk, do this instead!",
                    trendingElement = "Warm nostalgic acoustic guitar melody with gentle boiling tea sounds",
                    script = ReelScript(
                        sec0_3 = "Visual: Crushing fresh adrak and elaichi in a traditional mortar pestle with loud rhythmic thuds. Line: 'Dadi ne sikhayi thi ye chai trick: Adrak pehle nahi, is exact step pe daalna.'",
                        sec3_15 = "Visual: Boiling water with spices until it turns rich brown BEFORE pouring milk so the ginger doesn't curdle or lose potency.",
                        sec15_25 = "Visual: High pour of piping hot caramel-colored karak chai through strainer into clay kulhad with steam rising.",
                        sec25_30 = "Visual: Taking a sip with warm cozy smile. Line: 'Tag your chai partner jiske bina shaam adhoori lagti hai!'",
                    ),
                    caption = "Karak chai masterclass from grandma's kitchen ☕ Perfect aroma, zero bitterness. Share with a chai addict!",
                    hashtags = listOf("#ChaiLovers", "#AdrakChai", "#KarakChai", "#IndianKitchen", "#ComfortFood"),
                    whyItWorks = "Emotional storytelling combined with practical kitchen wisdom creates high emotional affinity and shares.",
                    formatType = "Storytelling & Nostalgia",
                    difficulty = "Easy Shoot Today (Stove & saucepan)",
                    hookType = "Pattern Interrupt"
                )
            )
        )
    }

    private fun getFinancePreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Personal Finance & Investing",
            dateContext = "Tax-saving season & SIP compounding awareness trends",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "Stop Keeping Money in Savings Account!",
                    hookLine = if (isHinglish) "Stop keeping your salary in a regular savings account! Tum har saal 5% loss kar rahe ho." else "Stop parking your emergency fund in a basic savings account! You are losing 5% to inflation every year.",
                    trendingElement = "Alert siren sound effect followed by clean modern tech-explainer BGM",
                    script = ReelScript(
                        sec0_3 = "Visual: Tearing a fake ₹500 currency note in half (or paper slip with currency symbol). Line: 'Agar tumhara paisa normal savings account mein pada hai, to tum technically daily apna paisa jala rahe ho!'",
                        sec3_15 = "Visual: On-screen graphics showing Bank Interest (3%) vs Inflation Rate (7%). Line: 'Inflation 6-7% pe hai aur bank sirf 2.7% deta hai. Har saal tumhari purchasing power 4% kam ho rahi hai.'",
                        sec15_25 = "Visual: Showing phone screen opening Auto-Sweep facility / Liquid Mutual Fund. Line: 'Solution: Apne bank app mein 'Auto-Sweep Facility' enable karo, ya emergency fund ko Overnight / Liquid Fund mein park karo to earn 6.5-7% risk-free.'",
                        sec25_30 = "Visual: Pointing to follow button. Line: 'Follow Reel Guru for zero-BS money hacks jo school mein nahi sikhaye gaye!'",
                    ),
                    caption = "Inflation is silently stealing your hard-earned savings 📉 Enable auto-sweep today! Save this reel so you don't forget.",
                    hashtags = listOf("#PersonalFinance", "#MoneyHacks", "#InvestingTips", "#InflationProof", "#FinancialFreedom"),
                    whyItWorks = "Fear-of-loss hook hits pain point immediately and provides a 1-minute actionable banking fix.",
                    formatType = "Myth Buster & Immediate Fix",
                    difficulty = "Easy Shoot Today (Talking head + phone screen)",
                    hookType = "Pattern Interrupt"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "₹5,000 Monthly SIP Magic at Age 22 vs 32",
                    hookLine = if (isHinglish) "Sirf 10 saal late hone ki wajah se tumne 1.5 CRORE ka nuksan kar liya!" else "Starting just 10 years late costs you over $200,000! Look at these numbers.",
                    trendingElement = "Upbeat ticking clock transition with compounding chart animation",
                    script = ReelScript(
                        sec0_3 = "Visual: Writing ₹1,50,00,000 on a mini whiteboard and putting a big cross on it. Line: 'Sirf 10 saal late start karne ki wajah se tumne 1.5 CRORE rupees ka nuksaan kar liya!'",
                        sec3_15 = "Visual: Comparison split-screen showing Person A (starts ₹5k SIP at 22, stops at 32) vs Person B (starts at 32 till 55).",
                        sec15_25 = "Visual: Revealing compounding calculator graph. Line: 'Person A sirf 6 lakh invest karke retirement pe 2.8 Crore banata hai, jabki Person B 18 lakh invest karke bhi peeche reh jata hai. Compounding ka asli superpower hai TIME.'",
                        sec25_30 = "Visual: Handing a virtual coin to camera. Line: 'Comment 'SIP' aur main bhejunga beginner index fund guide for FREE! Follow for smart money.'",
                    ),
                    caption = "Compounding rewards patience, not timing ⏳ Start small, but start TODAY. Share with someone in their 20s!",
                    hashtags = listOf("#MutualFunds", "#SIPInvesting", "#CompoundInterest", "#WealthCreation", "#MoneyMindset"),
                    whyItWorks = "Shocking numbers hook stops thumb scrolls cold; actionable DM trigger drives engagement loop.",
                    formatType = "Educational Compounding Breakdown",
                    difficulty = "Easy Shoot Today (Whiteboard or simple tablet screen)",
                    hookType = "Shocking Stat"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "How I Cut My Credit Card Bill by 30%",
                    hookLine = if (isHinglish) "Credit card companies nahi chahti ki tum ye 1 trick jaano!" else "Credit card companies will hate me for exposing this 1 statement date secret!",
                    trendingElement = "Cyberpunk synth beat with sleek glitch text reveals",
                    script = ReelScript(
                        sec0_3 = "Visual: Showing credit card between two fingers right up to lens. Line: 'Credit card companies nahi chahti ki tum ye 1 billing trick jaano.'",
                        sec3_15 = "Visual: Calendar on screen showing Statement Date vs Due Date. Line: 'Agar tum large purchases statement generate hone ke agle din karte ho, to tumhe milte hain poore 45-50 days ka 0% interest period.'",
                        sec15_25 = "Visual: Showing CIBIL score dashboard at 790+. Line: 'Aur utilization hamesha 30% ke andar rakho statement date se pehle pay karke. Tumhara CIBIL score rocket ban jayega.'",
                        sec25_30 = "Visual: Smirk and bookmark motion. Line: 'Save this before banks take this down, aur share karo credit card holders ke saath!'",
                    ),
                    caption = "Master your credit card before it masters your salary 💳 45 days interest-free hack that actually works.",
                    hashtags = listOf("#CreditCardHacks", "#CIBILScore", "#SmartMoney", "#PersonalFinanceTips", "#FinancialLiteracy"),
                    whyItWorks = "Curiosity and 'insider knowledge' framed as a conflict against big institutions drives high virality.",
                    formatType = "Insider Secret / Life Hack",
                    difficulty = "Easy Shoot Today (Just hold your credit card with numbers hidden)",
                    hookType = "Curiosity Gap"
                )
            )
        )
    }

    private fun getTechPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Tech & AI Tools",
            dateContext = "Latest AI automation & hidden smartphone features wave",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "This Free AI Tool Feels Illegal to Know",
                    hookLine = if (isHinglish) "Ye ek AI tool itna powerful hai ki iska free hona illegal lagta hai!" else "This free AI tool is so unbelievably good it honestly feels illegal to know.",
                    trendingElement = "Futuristic tech bass drop on screen turn-on",
                    script = ReelScript(
                        sec0_3 = "Visual: Turning laptop screen towards camera with glowing interface. Line: 'Ye ek free AI tool itna powerful hai ki iska free hona illegal lagta hai!'",
                        sec3_15 = "Visual: Screen recording showing how uploading a 1-hour YouTube link or PDF creates 10 ready reels, summaries, and mind maps in 15 seconds.",
                        sec15_25 = "Visual: Live demo showing automatic subtitle styling and B-roll generation with one click.",
                        sec25_30 = "Visual: Pointing to caption. Line: 'Tool ka direct link aur prompts maine caption mein drop kar diye hain. Abhi save karo!'",
                    ),
                    caption = "Stop wasting 6 hours on manual editing 🤖 This AI workflow will 10x your productivity. Link details below!",
                    hashtags = listOf("#AITools", "#TechHacks", "#ProductivityTools", "#AIGadgets", "#ContentCreation"),
                    whyItWorks = "The 'feels illegal to know' hook pattern is mathematically one of the highest retention openers on short-form platforms.",
                    formatType = "Trending Audio & Screen Reveal",
                    difficulty = "Easy Shoot Today (Laptop screen + phone camera)",
                    hookType = "Bold Claim"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "Hidden Android Feature You Don't Use",
                    hookLine = if (isHinglish) "Agar tum Android phone use karte ho aur ye setting off nahi ki, to tumhara battery drain ho raha hai!" else "If you use an Android phone and haven't turned off this setting, your battery is draining fast!",
                    trendingElement = "Sharp click sound effects on every toggle touch",
                    script = ReelScript(
                        sec0_3 = "Visual: Holding phone with battery at 12% in red. Line: 'Agar tum Android phone use karte ho aur ye 1 setting off nahi ki, to tumhara phone secretly battery aur data drain kar raha hai!'",
                        sec3_15 = "Visual: Fast step-by-step navigation in Settings -> Google -> Nearby Share / Device Connections -> Turn Off Scanning.",
                        sec15_25 = "Visual: Showing battery graph stabilized. Line: 'Isse phone background mein bina wajah devices search karna band kar dega aur battery life 25% improve ho jayegi.'",
                        sec25_30 = "Visual: Thumbs up to camera. Line: 'Follow Reel Guru for daily smart tech secrets!'",
                    ),
                    caption = "Stop unnecessary battery drain on Android with this 15-second tweak 🔋 Try it right now!",
                    hashtags = listOf("#AndroidHacks", "#TechTips", "#SmartphoneTricks", "#PhoneSettings", "#AndroidSecret"),
                    whyItWorks = "Direct utility hook where users test the setting on their own phone immediately while watching.",
                    formatType = "Direct Utility & Screen Tutorial",
                    difficulty = "Easy Shoot Today (Screen record or over-the-shoulder)",
                    hookType = "Relatable Callout"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "3 AI Prompts That Replace 40 Hours of Work",
                    hookLine = if (isHinglish) "ChatGPT se generic answers milte hain kyunki tum prompts galat likhte ho." else "You get useless generic answers from ChatGPT because you're prompting it all wrong.",
                    trendingElement = "Keyboard clack ASMR with sleek lo-fi ambient soundtrack",
                    script = ReelScript(
                        sec0_3 = "Visual: Shaking head looking at screen. Line: 'ChatGPT se bekaar answers milte hain kyunki tum normal humans ki tarah baat kar rahe ho, AI ki tarah nahi!'",
                        sec3_15 = "Visual: Showing 'Act as a senior CMO with 15 years experience and critique this plan ruthlessly'.",
                        sec15_25 = "Visual: Side-by-side comparison of mediocre output vs mind-blowing structured output. Line: 'Prompt 1: The Persona Anchor. Prompt 2: The Step-by-Step Chain. Prompt 3: The Edge-Case Critic.'",
                        sec25_30 = "Visual: Pointing to comments. Line: 'Comment 'PROMPT' aur main saare copy-pasteable templates DM kar dunga!'",
                    ),
                    caption = "Master prompt engineering in 30 seconds ⚡ Upgrade your output from junior intern to expert strategist.",
                    hashtags = listOf("#ChatGPTHacks", "#PromptEngineering", "#AIProductivity", "#TechReels", "#WorkSmart"),
                    whyItWorks = "Curiosity gap combined with actionable DM automations turns casual viewers into loyal followers.",
                    formatType = "Listicle & Prompt Breakdown",
                    difficulty = "Easy Shoot Today (Screen recording / facecam)",
                    hookType = "Curiosity Gap"
                )
            )
        )
    }

    private fun getFashionPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Fashion & Styling",
            dateContext = "Minimalist capsule wardrobe & budget outfit aesthetics",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "How to Look Expensive on a ₹1000 Budget",
                    hookLine = if (isHinglish) "Stop wearing oversized printed tees agar tumhe classy look chahiye!" else "Stop wearing flashy printed shirts if you want to look effortlessly stylish!",
                    trendingElement = "Snappy snap transitions matching outfit changes on beat",
                    script = ReelScript(
                        sec0_3 = "Visual: Holding a loud gaudy graphic t-shirt and tossing it out of frame. Line: 'Stop wearing printed t-shirts agar tumhe effortlessly classy dikhna hai!'",
                        sec3_15 = "Visual: Snap fingers -> Clean solid neutral knit polo in beige + pleated tailored trousers in charcoal. Line: 'Rule 1: Solid textures beats cheap prints. Rule 2: Iron your collar sharp.'",
                        sec15_25 = "Visual: Adding a simple minimalist silver watch and classic white leather sneakers. Line: 'Minimal accessories and monochrome colors instantly elevate a ₹800 outfit to look like ₹5,000.'",
                        sec25_30 = "Visual: 360-degree spin to camera. Line: 'Save this for your next weekend shopping spree and follow for daily style upgrades!'",
                    ),
                    caption = "Money can't buy style, but good color coordination can 🕶️ Master the clean aesthetic on a budget.",
                    hashtags = listOf("#MenStyle", "#FashionHacks", "#CapsuleWardrobe", "#OutfitInspo", "#OldMoneyAesthetic"),
                    whyItWorks = "Visual transformation on the beat with practical budget rules creates high save rates.",
                    formatType = "Transition & Outfit Transformation",
                    difficulty = "Easy Shoot Today (Basic wardrobe pieces)",
                    hookType = "Pattern Interrupt"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "1 White Shirt, 3 Completely Different Vibes",
                    hookLine = if (isHinglish) "Tumhe naye kapde nahi chahiye, bas is 1 shirt ko 3 tareeqe se pehenna seekho!" else "You don't need a new wardrobe, you just need to style this one shirt 3 ways!",
                    trendingElement = "Chic runway beat with seamless whip pan transitions",
                    script = ReelScript(
                        sec0_3 = "Visual: Holding plain white button-down shirt. Line: 'Tumhe naye kapde khareedne ki zaroorat nahi, bas is 1 basic white shirt ko style karna seekho.'",
                        sec3_15 = "Visual: Vibe 1: Office formal tucked with belt. Vibe 2: Casual unbuttoned over a black tank with rolled-up sleeves.",
                        sec15_25 = "Visual: Vibe 3: Semi-casual layered with an overshirt and sunglasses for date night.",
                        sec25_30 = "Visual: Posing with smile. Line: 'Which look is your favorite: 1, 2, or 3? Tell me in the comments!'",
                    ),
                    caption = "The most versatile piece in your wardrobe 🤍 Which styling was the cleanest: 1, 2, or 3?",
                    hashtags = listOf("#StyleTips", "#WhiteShirtStyle", "#FashionReels", "#OOTD", "#WardrobeEssentials"),
                    whyItWorks = "Comment-triggering question ('1, 2 or 3?') stimulates active viewer comments boosting algorithmic push.",
                    formatType = "Listicle & Style Inspiration",
                    difficulty = "Medium Production (Quick outfit swaps)",
                    hookType = "Direct Value Promise"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "The 3-Second Shoe Rule Nobody Taught You",
                    hookLine = if (isHinglish) "90% log sabse pehle tumhare joote dekhte hain, aur tum ye mistake kar rahe ho." else "90% of people judge an outfit by the footwear first, and you're making this mistake.",
                    trendingElement = "Close-up footstep sync with heavy bass boom",
                    script = ReelScript(
                        sec0_3 = "Visual: Looking down at dusty creased sneakers vs polished clean sole. Line: '90% log sabse pehle tumhare footwear notice karte hain aur ye 1 galti poora outfit kharab kar deti hai.'",
                        sec3_15 = "Visual: Running sports running shoes paired with slim denim vs clean minimal court sneakers. Line: 'Gym ke running shoes ko casual outings pe pehenna band karo.'",
                        sec15_25 = "Visual: 5-second sneaker cleaning sponge demo restoring bright white edges instantly.",
                        sec25_30 = "Visual: Confident walk towards camera. Line: 'Follow for realistic styling advice that actually makes sense!'",
                    ),
                    caption = "Never let dirty gym kicks ruin an otherwise great outfit 👟 The sneaker rules you need to know.",
                    hashtags = listOf("#SneakerCare", "#StyleMistakes", "#FashionAdvice", "#StreetwearStyle", "#MenFashion"),
                    whyItWorks = "Relatable pain point and immediate corrective advice creates bookmark-worthy content.",
                    formatType = "Quick Tip & Footwear Rule",
                    difficulty = "Easy Shoot Today (Just pair of shoes and camera)",
                    hookType = "Curiosity Gap"
                )
            )
        )
    }

    private fun getProductivityPreset(lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = "Productivity & Focus",
            dateContext = "Deep work techniques & beating digital fatigue",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "The 5-Minute Rule to Beat Procrastination",
                    hookLine = if (isHinglish) "Agar tum bhi ghanton phone scroll karke kaam delay karte ho, to ye 1 psychology trick dekho." else "If you spend hours scrolling and delaying hard work, this 1 brain trick changes everything.",
                    trendingElement = "Metronome ticking sound speeding up then smoothly transitioning into calm lo-fi beat",
                    script = ReelScript(
                        sec0_3 = "Visual: Scrolling phone mindlessly in bed, then throwing phone across the mattress. Line: 'Agar tum bhi ghanton reel scroll karke apna kaam delay karte ho, to ye trick suno.'",
                        sec3_15 = "Visual: Sitting down at desk, opening notebook, setting a timer for 5:00 minutes. Line: 'Dimaag ko bolo: Main sirf 5 minute kaam karunga, uske baad quit kar dunga. Psychology ke hisaab se 80% friction sirf START karne mein hota hai.'",
                        sec15_25 = "Visual: Time-lapse of working deeply with headset on. Line: 'Once you start, Zeigarnik effect kicks in aur tum easily 1 ghante tak deep work state mein chale jaate ho.'",
                        sec25_30 = "Visual: Looking at camera with satisfied grin. Line: 'Abhi phone side rakho aur 5 minute start karo! Save this for when procrastination hits.'",
                    ),
                    caption = "80% of procrastination is just the initial friction to sit down 🧠 Hack your brain with the 5-minute rule.",
                    hashtags = listOf("#ProductivityHacks", "#DeepWork", "#BeatProcrastination", "#FocusTips", "#SelfImprovement"),
                    whyItWorks = "Directly addresses the immediate viewer state (scrolling reels on phone) to break the loop with high impact.",
                    formatType = "Psychology Breakdown & Personal Habit",
                    difficulty = "Easy Shoot Today (Desk, notebook, phone timer)",
                    hookType = "Relatable Callout"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "Stop Multitasking! It's Destroying Your IQ",
                    hookLine = if (isHinglish) "Stop switching tabs every 2 minutes! Isse tumhara attention span khatam ho raha hai." else "Stop switching tabs every 2 minutes! Science proves it lowers your working IQ by 10 points.",
                    trendingElement = "Dramatic browser notification chimes overlapping chaotically into silence",
                    script = ReelScript(
                        sec0_3 = "Visual: 50 open browser tabs on screen with chaotic notification sounds. Line: 'Stop switching tabs every 2 minutes! Isse tumhara IQ temporary 10 points drop ho raha hai.'",
                        sec3_15 = "Visual: Stanford research paper graphic on screen: 'Context switching penalty costs 23 minutes to refocus after each interruption.'",
                        sec15_25 = "Visual: Activating 'Do Not Disturb' + Full-screen 1 document mode. Line: 'Do single-task batching. 45 minutes on 1 single tab, then 10 minutes break. Output 3x ho jayega.'",
                        sec25_30 = "Visual: Pointing to follow. Line: 'Follow Reel Guru for science-backed focus strategies.'",
                    ),
                    caption = "Context switching is silently ruining your workday ⚡ Single-tasking is the real superpower.",
                    hashtags = listOf("#TimeManagement", "#FocusMode", "#StudyWithMe", "#ProductivityTips", "#WorkSmarter"),
                    whyItWorks = "Scientific study backed claim creates high authority and establishes instant creator credibility.",
                    formatType = "Scientific Myth Buster",
                    difficulty = "Easy Shoot Today (Computer screen + voiceover)",
                    hookType = "Pattern Interrupt"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "My 3 Non-Negotiable Morning Habits",
                    hookLine = if (isHinglish) "Ye 3 cheezein subah karne ke baad mera daily output double ho gaya." else "Implementing these 3 morning rules doubled my daily productive output in 14 days.",
                    trendingElement = "Peaceful morning ambient bird sounds + sunrise time-lapse aesthetic",
                    script = ReelScript(
                        sec0_3 = "Visual: Drinking glass of water facing morning window sunlight. Line: 'Ye 3 cheezein subah implement karne ke baad mera focus poore din sharp rehta hai.'",
                        sec3_15 = "Visual: Rule 1: No phone screen for first 30 minutes. Rule 2: 10 minutes of direct morning sunlight for cortisol reset.",
                        sec15_25 = "Visual: Writing 3 priority MITs (Most Important Tasks) on sticky note. Line: 'Rule 3: Start your hardest task before checking any emails or messages.'",
                        sec25_30 = "Visual: Coffee cup clinking desk. Line: 'Share this with your morning accountability partner!'",
                    ),
                    caption = "Win the morning, win the entire day ☀️ 3 simple habits that compound over time.",
                    hashtags = listOf("#MorningRoutine", "#ProductiveMorning", "#DailyHabits", "#LifeHacks", "#SuccessMindset"),
                    whyItWorks = "Aesthetic morning routine format has huge evergreen appeal across Instagram and YouTube Shorts.",
                    formatType = "Storytelling & Morning Routine",
                    difficulty = "Easy Shoot Today (Natural daylight near window)",
                    hookType = "Bold Claim"
                )
            )
        )
    }

    private fun getGenericCreatorPreset(niche: String, lang: String): ReelIdeaResponse {
        val isHinglish = lang.contains("Hinglish", ignoreCase = true) || lang.contains("Hindi", ignoreCase = true)
        return ReelIdeaResponse(
            niche = niche,
            dateContext = "Current viral algorithm focus on retention & fast pattern-interrupts",
            ideas = listOf(
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "The #1 Mistake in $niche",
                    hookLine = if (isHinglish) "Stop doing this in $niche! Isse pata chalta hai tum amateur ho." else "Stop doing this in $niche! It immediately reveals you're an amateur.",
                    trendingElement = "Sharp bass drop on second 3 with dramatic push-in zoom",
                    script = ReelScript(
                        sec0_3 = "Visual: Direct close-up with finger pointing at camera. Line: 'Stop doing this in $niche! Isse pata chalta hai tum basic beginner mistakes kar rahe ho.'",
                        sec3_15 = "Visual: Showing the common wrong way vs the expert way with split-screen comparison.",
                        sec15_25 = "Visual: Demonstration of the exact pro workflow that delivers 10x better results in half the time.",
                        sec25_30 = "Visual: Pointing down to caption. Line: 'Comment 'GUIDE' aur main step-by-step breakdown bhejunga! Save this reel.'",
                    ),
                    caption = "Don't make this common $niche mistake! 💡 Here is how top creators and pros handle it.",
                    hashtags = listOf("#$niche".replace(" ", ""), "#CreatorTips", "#TrendingReels", "#ViralHacks", "#SkillBuilding"),
                    whyItWorks = "Pattern interrupt hook commands immediate attention and establishes authority in $niche.",
                    formatType = "Myth Buster / Pattern Interrupt",
                    difficulty = "Easy Shoot Today (No props needed)",
                    hookType = "Pattern Interrupt"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "What Nobody Tells You About $niche",
                    hookLine = if (isHinglish) "90% log $niche mein ye galti karte hain aur unhe pata bhi nahi chalta!" else "90% of people in $niche make this costly mistake without even realizing it.",
                    trendingElement = "Suspenseful lo-fi synth with clean talking-head setup",
                    script = ReelScript(
                        sec0_3 = "Visual: Leaning into camera with whispered confidence. Line: '90% log $niche mein ye galti karte hain aur unhe pata bhi nahi chalta.'",
                        sec3_15 = "Visual: Breakdown of the hidden friction point that slows down progress for everyone in $niche.",
                        sec15_25 = "Visual: The single mindset or technique shift that changed everything for top performers.",
                        sec25_30 = "Visual: Friendly smile with thumbs up. Line: 'Follow Reel Guru for daily actionable secrets in $niche!'",
                    ),
                    caption = "The unspoken truth about $niche that will save you months of trial and error 🎯",
                    hashtags = listOf("#$niche".replace(" ", ""), "#GrowthTips", "#ReelStrategy", "#LearnOnline", "#ProTips"),
                    whyItWorks = "Curiosity-gap hook with valuable insider perspective drives high watch-time and repeat views.",
                    formatType = "Insider Storytelling & Value",
                    difficulty = "Easy Shoot Today (Just phone & good lighting)",
                    hookType = "Curiosity Gap"
                ),
                ReelIdea(
                    id = UUID.randomUUID().toString(),
                    ideaTitle = "3 Rules in $niche That Changed Everything",
                    hookLine = if (isHinglish) "Ye ek cheez chhod di to $niche mein results 3x ho gaye!" else "Once I stopped doing this one thing in $niche, my results literally tripled.",
                    trendingElement = "Energetic upbeat transition beat with fast text pop-ups",
                    script = ReelScript(
                        sec0_3 = "Visual: Holding up 3 fingers sequentially with snap sounds. Line: 'Ye ek cheez chhod di to $niche mein results 3x ho gaye!'",
                        sec3_15 = "Visual: Fast numbered checklist: Rule 1: Consistency over perfection. Rule 2: Measuring the right metric.",
                        sec15_25 = "Visual: Rule 3: The exact execution step that converts effort into noticeable results.",
                        sec25_30 = "Visual: Pointing to share button. Line: 'Share this with someone working in $niche!'",
                    ),
                    caption = "3 non-negotiable rules for anyone serious about $niche 🚀 Implement rule #2 today.",
                    hashtags = listOf("#$niche".replace(" ", ""), "#DailyGrowth", "#ActionableAdvice", "#CreatorEconomy", "#Inspiration"),
                    whyItWorks = "Bold claim hook combined with scannable 3-part listicle ensures viewers stay until the final CTA.",
                    formatType = "Quick Listicle & Action Plan",
                    difficulty = "Easy Shoot Today (Clean background & clear voice)",
                    hookType = "Bold Claim"
                )
            )
        )
    }
}
